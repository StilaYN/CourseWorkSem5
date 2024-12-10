package ru.database.coursework.api.employee.model;

import java.sql.Date;

public record Employee(
        Integer id,
        String lastName,
        String firstName,
        String middleName,
        String sex,
        Date birthDate,
        String city,
        String street,
        String houseNumber,
        String apartmentNumber,
        String specialization,
        String experience,
        String documentType,
        String documentNumber,
        String education,
        Integer salary
) {


}
