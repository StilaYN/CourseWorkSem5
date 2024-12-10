package ru.database.coursework.api.specialization;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.specialization.model.SpecializationCreationRequest;
import ru.database.coursework.api.specialization.model.SpecializationFilter;
import ru.database.coursework.api.specialization.model.SpecializationUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.SpecializationService;

@Controller
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService specializationService;

    @PostMapping(ApiPaths.SPECIALIZATION_CREATE)
    public String createDocument(SpecializationCreationRequest request) {
        specializationService.createSpecialization(request);
        return "redirect:/specialization/list";
    }

    @GetMapping(ApiPaths.SPECIALIZATION_CREATE)
    public String getCreateDocumentPage(Model model) {
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.specializations")));
        return "specialization/create";
    }

    @GetMapping(ApiPaths.SPECIALIZATION_LIST)
    public String getDocumentTypeList(@ParameterObject SpecializationFilter specializationFilter, Model model) {
        model.addAttribute("specializationList", specializationService.getAllSpecialization(specializationFilter));
        model.addAttribute("searchResult", specializationFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.specializations")));
        return "specialization/list";
    }

    @PostMapping(ApiPaths.SPECIALIZATION_UPDATE)
    public String updateSpecialization(SpecializationUpdateRequest specializationUpdateRequest) {
        if (specializationUpdateRequest.delete()) {
            specializationService.deleteSpecialization(specializationUpdateRequest.id());
        } else {
            specializationService.updateSpecialization(specializationUpdateRequest);
        }
        return "redirect:/specialization/list";
    }

    @GetMapping(ApiPaths.SPECIALIZATION_UPDATE)
    public String getUpdateSpecializationPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("specialization", specializationService.getSpecializationById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.specializations")));
        return "specialization/update";
    }

}
