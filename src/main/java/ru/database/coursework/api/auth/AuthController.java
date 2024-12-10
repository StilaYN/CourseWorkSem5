package ru.database.coursework.api.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.auth.model.AuthRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.AuthService;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(ApiPaths.LOGIN)
    public String login(AuthRequest authRequest) {
        authService.auth(authRequest);
        return "redirect:/homepage";
    }

    @GetMapping(ApiPaths.LOGIN)
    public String getIndex(){
        return "auth/auth";
    }

    @GetMapping(ApiPaths.HOMEPAGE)
    public String getIndex(Model model) {
        model.addAttribute("menu", Context.menu);
        return "index";
    }

}
