package ru.database.coursework.api.other;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.core.Context;

@Controller
public class OtherController {

    @GetMapping(ApiPaths.SETTINGS)
    public String getSettings(Model model) {
        model.addAttribute("menu", Context.menu);
        return "other/settings";
    }

    @GetMapping(ApiPaths.ABOUT)
    public String getAbout(Model model) {
        model.addAttribute("menu", Context.menu);
        return "other/about";
    }

    @GetMapping(ApiPaths.CONTENT)
    public String getContent(Model model) {
        model.addAttribute("menu", Context.menu);
        return "other/content";
    }
}
