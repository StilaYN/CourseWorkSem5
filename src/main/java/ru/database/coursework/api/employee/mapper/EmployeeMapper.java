package ru.database.coursework.api.employee.mapper;

import org.mapstruct.Mapper;
import ru.database.coursework.api.employee.model.Employee;
import ru.database.coursework.api.employee.model.EmployeeTableHeader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {

    List<Map<String, String>> map(List<Employee> employees);

    default Map<String, String> map(Employee employee) {
        Map<String, String> map = new HashMap<>();
        map.put(EmployeeTableHeader.ID, employee.id().toString());
        map.put(EmployeeTableHeader.LAST_NAME, employee.lastName());
        map.put(EmployeeTableHeader.FIRST_NAME, employee.firstName());
        map.put(EmployeeTableHeader.MIDDLE_NAME, employee.middleName());
        map.put(EmployeeTableHeader.SEX, employee.sex());
        map.put(EmployeeTableHeader.BIRTH_DATE, employee.birthDate().toString());
        map.put(EmployeeTableHeader.CITY, employee.city());
        map.put(EmployeeTableHeader.STREET, employee.street());
        map.put(EmployeeTableHeader.HOUSE_NUMBER, employee.houseNumber());
        map.put(EmployeeTableHeader.APARTMENT_NUMBER, employee.apartmentNumber());
        map.put(EmployeeTableHeader.SPECIALIZATION, employee.specialization());
        map.put(EmployeeTableHeader.EXPERIENCE, employee.experience());
        map.put(EmployeeTableHeader.DOCUMENT_TYPE, employee.documentType());
        map.put(EmployeeTableHeader.DOCUMENT_NUMBER, employee.documentNumber());
        map.put(EmployeeTableHeader.EDUCATION, employee.education());
        map.put(EmployeeTableHeader.SALARY, employee.salary().toString());
        return map;
    }
}
