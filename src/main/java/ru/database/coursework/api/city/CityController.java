package ru.database.coursework.api.city;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.city.model.CityCreationRequest;
import ru.database.coursework.api.city.model.CityFilter;
import ru.database.coursework.api.city.model.CityUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.CityService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping(ApiPaths.CITY_CREATE)
    public String createCity(CityCreationRequest cityCreationRequest) {
        cityService.createCity(cityCreationRequest);
        return "redirect:/city/list";
    }

    @GetMapping(ApiPaths.CITY_CREATE)
    public String getNewCityPage(Model model) {
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.cities")));
        return "city/create";
    }

    @GetMapping(ApiPaths.CITY_LIST)
    public String getCityListPage(@ParameterObject CityFilter cityFilter, Model model) {
        model.addAttribute("cityList", cityService.getAllCities(cityFilter));
        model.addAttribute("searchResult", cityFilter.cityName());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.cities")));
        return "city/list";
    }

    @PostMapping(ApiPaths.CITY_UPDATE)
    public String updateCity(CityUpdateRequest cityUpdateRequest) {
        if (cityUpdateRequest.delete()) {
            cityService.deleteCityById(cityUpdateRequest.id());
        } else {
            cityService.updateCity(cityUpdateRequest);
        }
        return "redirect:/city/list";
    }

    @GetMapping(ApiPaths.CITY_UPDATE)
    public String getUpdateCityPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("city", cityService.getCityById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.cities")));
        return "city/update";
    }

    @PostMapping(ApiPaths.CITY_DELETE)
    public String deleteCity(@PathVariable("id") int id) {
        cityService.deleteCityById(id);
        return "redirect:/city/list";
    }

}
