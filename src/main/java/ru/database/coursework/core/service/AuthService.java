package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.auth.model.AuthRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.entity.AuthEntity;
import ru.database.coursework.core.entity.UserPrivileges;
import ru.database.coursework.core.mapper.AuthMapper;
import ru.database.coursework.core.repository.AuthRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MenuService menuService;

    private final AuthRepository authRepository;

    private final AuthMapper authMapper;

    public void auth(AuthRequest authRequest) {
        AuthEntity authEntity = login(authRequest);
        if (authEntity != null) {
            List<UserPrivileges> userPrivileges = authRepository.findUserPrivileges(authEntity.id());
            Context.authorityMap = authMapper.map(userPrivileges);
            Context.tablesName = authMapper.mapToTableName(userPrivileges);
            menuService.findAll();
        }
    }

    private AuthEntity login(AuthRequest authRequest) {
        return authRepository.findByLogin(authRequest.username(), authRequest.password()).orElse(null);
    }
}
