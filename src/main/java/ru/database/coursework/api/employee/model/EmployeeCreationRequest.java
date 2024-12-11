package ru.database.coursework.api.employee.model;

import java.sql.Date;

public record EmployeeCreationRequest(
        String lastName,
        String firstName,
        String middleName,
        String sex,
        Date birthDate,
        Integer cityId,
        Integer streetId,
        String houseNumber,
        String apartmentNumber,
        Integer specializationId,
        String experience,
        Integer documentTypeId,
        String documentNumber,
        String education,
        Integer salary
) {

}
