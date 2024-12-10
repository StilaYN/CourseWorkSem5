package ru.database.coursework.api.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.database.coursework.core.service.EmployeeService;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

//    @GetMapping(ApiPaths.EMPLOYEES_BY_ID)
//    public String getEmployeeById(@PathVariable int id, Model model){
//        SingleData singleData = employeeService.getEmployeeById(id);
//
//    }

//    @GetMapping(ApiPaths.EMPLOYEES)
//    public String getAllEmployees(EmployeeFilter employeeFilter, Model model){
//        TableData tableData = employeeService.getAllEmployees(employeeFilter);
//        model.addAttribute("tableTitle", EmployeeTableHeader.EMPLOYEE_TABLE_TITLE);
//        model.addAttribute("basePath", ApiPaths.EMPLOYEES);
//        model.addAttribute("idName",  EmployeeTableHeader.ID);
//        model.addAttribute("columnName", tableData.getColumnNames());
//        model.addAttribute("mapData", tableData.getData());
//        return "table";
//
//    }

}
