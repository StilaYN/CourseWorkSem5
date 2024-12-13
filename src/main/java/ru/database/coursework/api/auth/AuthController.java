package ru.database.coursework.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.auth.model.AuthRequest;
import ru.database.coursework.api.auth.model.ChangePasswordRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.exception.WrongPasswordException;
import ru.database.coursework.core.service.AuthService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiPaths.LOGIN)
    public String login(AuthRequest authRequest, BindingResult result) {
        try {
            authService.auth(authRequest);
        } catch (WrongPasswordException e) {
            ObjectError error = new ObjectError("global", e.getMessage());
            result.addError(error);
            if (result.hasErrors()) {
                return "auth/auth";
            }
        }
        return "redirect:/homepage";
    }

    @GetMapping(ApiPaths.LOGIN)
    public String getIndex(AuthRequest authRequest){
        return "auth/auth";
    }

    @GetMapping(ApiPaths.HOMEPAGE)
    public String getIndex(Model model) {
        model.addAttribute("menu", Context.menu);
        return "index";
    }

    @PostMapping(ApiPaths.CHANGE_PASSWORD)
    public String changePassword(ChangePasswordRequest changePasswordRequest) {
        authService.changePassword(changePasswordRequest);
        return "redirect:/homepage";
    }

    @GetMapping(ApiPaths.CHANGE_PASSWORD)
    public String getChangePassword(Model model) {
        model.addAttribute("menu", Context.menu);
        return "auth/change";
    }

}
