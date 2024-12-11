package ru.database.coursework.api.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.employee.model.Employee;
import ru.database.coursework.api.employee.model.EmployeeCreationRequest;
import ru.database.coursework.api.employee.model.EmployeeFilter;
import ru.database.coursework.api.employee.model.EmployeeUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.CityService;
import ru.database.coursework.core.service.DocumentTypeService;
import ru.database.coursework.core.service.EmployeeService;
import ru.database.coursework.core.service.SpecializationService;
import ru.database.coursework.core.service.StreetService;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    private final CityService cityService;

    private final DocumentTypeService documentTypeService;

    private final StreetService streetService;

    private final SpecializationService specializationService;

    @PostMapping(ApiPaths.EMPLOYEE_CREATE)
    public String createEmployee(EmployeeCreationRequest employee) {
        employeeService.create(employee);
        return "redirect:/employee/list";
    }

    @GetMapping(ApiPaths.EMPLOYEE_CREATE)
    public String getCreateEmployeePage(Model model ) {
        model.addAttribute("cityList", cityService.getAllCities(null));
        model.addAttribute("streetList", streetService.getStreets(null));
        model.addAttribute("specializationList", specializationService.getAllSpecialization(null));
        model.addAttribute("documentTypeList", documentTypeService.getAllDocumentsType(null));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.employees")));
        return "employee/create";
    }

    @GetMapping(ApiPaths.EMPLOYEE_LIST)
    public String getEmployeeList(Model model, EmployeeFilter employeeFilter) {
        model.addAttribute("employeeList", employeeService.getAllEmployees(employeeFilter));
        model.addAttribute("searchResult", employeeFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.employees")));
        return "employee/list";
    }

    @PostMapping(ApiPaths.EMPLOYEE_UPDATE)
    public String updateEmployee(EmployeeUpdateRequest employee) {
        if (employee.delete()) {
            employeeService.delete(employee.id());
        } else {
            employeeService.update(employee);
        }
        return "redirect:/employee/list";
    }

    @GetMapping(ApiPaths.EMPLOYEE_UPDATE)
    public String getUpdateEmployeePage(@PathVariable("id") int id, Model model) {
        model.addAttribute("cityList", cityService.getAllCities(null));
        model.addAttribute("streetList", streetService.getStreets(null));
        model.addAttribute("specializationList", specializationService.getAllSpecialization(null));
        model.addAttribute("documentTypeList", documentTypeService.getAllDocumentsType(null));
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.employees")));
        return "employee/update";
    }

}
