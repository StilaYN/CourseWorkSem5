package ru.database.coursework.api.street.model;

public record StreetCreationRequest(
        String streetName,

        Integer cityId
) {

}
