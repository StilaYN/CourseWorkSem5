package ru.database.coursework.api.specialization.model;

public record SpecializationUpdateRequest(
        int id,
        String specializationName,
        boolean delete
) {

}
