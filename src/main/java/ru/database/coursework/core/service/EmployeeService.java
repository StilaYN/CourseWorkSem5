package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.employee.model.Employee;
import ru.database.coursework.api.employee.model.EmployeeCreationRequest;
import ru.database.coursework.api.employee.model.EmployeeFilter;
import ru.database.coursework.api.employee.model.EmployeeUpdateRequest;
import ru.database.coursework.core.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void create(EmployeeCreationRequest employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getAllEmployees(EmployeeFilter employeeFilter) {
        String template = (employeeFilter == null || employeeFilter.template() == null) ?
                null : "%" + employeeFilter.template() + "%";
        return employeeRepository.findAll(template);
    }

    public void update(EmployeeUpdateRequest employee) {
        employeeRepository.update(employee);
    }

    public void delete(int id) {
        employeeRepository.delete(id);
    }
}
