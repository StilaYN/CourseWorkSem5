package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.common.model.SingleData;
import ru.database.coursework.api.common.model.TableData;
import ru.database.coursework.api.employee.mapper.EmployeeMapper;
import ru.database.coursework.api.employee.model.Employee;
import ru.database.coursework.api.employee.model.EmployeeFilter;
import ru.database.coursework.api.employee.model.EmployeeTableHeader;
import ru.database.coursework.core.repository.EmployeeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public SingleData getEmployeeById(int id) {
        Employee employee = employeeRepository.getEmployeeById(id);
        return SingleData.builder()
                .fieldNames(EmployeeTableHeader.EMPLOYEES_TABLE_HEADER)
                .fieldValues(employeeMapper.map(employee))
                .build();
    }

    public TableData getAllEmployees(EmployeeFilter employee) {
        List<Employee> employees =  employeeRepository.getEmployees();
        return TableData.builder()
                .columnNames(EmployeeTableHeader.EMPLOYEES_TABLE_HEADER)
                .data(employeeMapper.map(employees))
                .build();
    }
}
