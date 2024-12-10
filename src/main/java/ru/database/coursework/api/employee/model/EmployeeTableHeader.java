package ru.database.coursework.api.employee.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeTableHeader {
    public static final String EMPLOYEE_TABLE_TITLE = "table.name.employees";
    public static final String ID = "header.employees.id";
    public static final String LAST_NAME = "header.employees.lastName";
    public static final String FIRST_NAME = "header.employees.firstName";
    public static final String MIDDLE_NAME = "header.employees.middleName";
    public static final String SEX = "header.employees.sex";
    public static final String BIRTH_DATE = "header.employees.birthDate";
    public static final String CITY = "header.employees.city";
    public static final String STREET = "header.employees.street";
    public static final String HOUSE_NUMBER = "header.employees.houseNumber";
    public static final String APARTMENT_NUMBER = "header.employees.apartmentNumber";
    public static final String SPECIALIZATION = "header.employees.specialization";
    public static final String EXPERIENCE = "header.employees.experience";
    public static final String DOCUMENT_TYPE = "header.employees.documentType";
    public static final String DOCUMENT_NUMBER = "header.employees.documentNumber";
    public static final String EDUCATION = "header.employees.education";
    public static final String SALARY = "header.employees.salary";

    public static final List<String> EMPLOYEES_TABLE_HEADER = List.of(
            ID,
            LAST_NAME,
            FIRST_NAME,
            MIDDLE_NAME,
            SEX,
            BIRTH_DATE,
            CITY,
            STREET,
            HOUSE_NUMBER,
            APARTMENT_NUMBER,
            SPECIALIZATION,
            EXPERIENCE,
            DOCUMENT_TYPE,
            DOCUMENT_NUMBER,
            EDUCATION,
            SALARY
    );

}
