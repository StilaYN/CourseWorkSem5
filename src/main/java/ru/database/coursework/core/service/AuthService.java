package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.auth.model.AuthRequest;
import ru.database.coursework.api.auth.model.ChangePasswordRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.entity.AuthEntity;
import ru.database.coursework.core.entity.UserPrivileges;
import ru.database.coursework.core.exception.WrongPasswordException;
import ru.database.coursework.core.mapper.AuthMapper;
import ru.database.coursework.core.repository.AuthRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final MenuService menuService;

    private final AuthRepository authRepository;

    private final AuthMapper authMapper;

    public void auth(AuthRequest authRequest) {
        Context.userId=null;
        Context.authorityMap=null;
        Context.tablesName=null;
        AuthEntity authEntity = login(authRequest);
//        log.info(authEntity.toString());
        if (authEntity != null) {
            List<UserPrivileges> userPrivileges = authRepository.findUserPrivileges(authEntity.id());
            Context.userId = authEntity.id();
            Context.authorityMap = authMapper.map(userPrivileges);
            Context.tablesName = authMapper.mapToTableName(userPrivileges);
            menuService.findAll();
        }
    }

    public void changePassword(ChangePasswordRequest changePasswordRequest) {
        AuthEntity authEntity = login(Context.userId, changePasswordRequest.oldPassword());

        if (authEntity != null) {
            log.info("auth entity {}", authEntity.toString());
            authRepository.changePassword(Context.userId, changePasswordRequest.newPassword());
        }
    }

    private AuthEntity login(AuthRequest authRequest) {
        return authRepository.findByLogin(authRequest.username(), authRequest.password()).orElseThrow(()->new WrongPasswordException("exception.wrong.login"));
    }

    private AuthEntity login(int id, String password) {
        return authRepository.findById(id, password).orElseThrow(()->new WrongPasswordException("exception.wrong.password"));
    }
}
