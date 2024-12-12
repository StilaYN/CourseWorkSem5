package ru.database.coursework.api.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.street.model.StreetCreationRequest;
import ru.database.coursework.api.street.model.StreetFilter;
import ru.database.coursework.api.street.model.StreetUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.StreetService;

@Controller
@RequiredArgsConstructor
public class StreetController {

    public final StreetService streetService;

    @PostMapping(ApiPaths.STREET_CREATE)
    public String createStreet(StreetCreationRequest streetCreationRequest) {
        streetService.createStreet(streetCreationRequest);
        return "redirect:/street/list";
    }

    @GetMapping(ApiPaths.STREET_CREATE)
    public String getCreateStreet(Model model) {
        model.addAttribute("cityList", streetService.getStreetCreationResponse().cities());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.streets")));
        return "street/create";
    }

    @GetMapping(ApiPaths.STREET_LIST)
    public String getStreetList(StreetFilter streetFilter, Model model) {
        model.addAttribute("streetList", streetService.getStreets(streetFilter));
        model.addAttribute("searchResult", streetFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.streets")));
        return "street/list";
    }

    @PostMapping(ApiPaths.STREET_UPDATE)
    public String updateStreet(StreetUpdateRequest streetUpdateRequest) {
        if (streetUpdateRequest.delete()){
            streetService.deleteStreet(streetUpdateRequest.id());
        } else {
            streetService.updateStreet(streetUpdateRequest);
        }
        return "redirect:/street/list";
    }

    @GetMapping(ApiPaths.STREET_UPDATE)
    public String getUpdateStreet(@PathVariable("id") int id, Model model) {
        model.addAttribute("street", streetService.getStreet(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.streets")));
        model.addAttribute("cityList", streetService.getStreetCreationResponse().cities());
        return "street/update";
    }

}
