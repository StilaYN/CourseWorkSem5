package ru.database.coursework.api.employee.model;

import java.sql.Date;

public record Employee(
        Integer id,
        String lastName,
        String firstName,
        String middleName,
        String sex,
        Date birthDate,
        Integer cityId,
        String city,
        Integer streetId,
        String street,
        String houseNumber,
        String apartmentNumber,
        Integer specializationId,
        String specialization,
        String experience,
        Integer documentTypeId,
        String documentType,
        String documentNumber,
        String education,
        Integer salary
) {


}
