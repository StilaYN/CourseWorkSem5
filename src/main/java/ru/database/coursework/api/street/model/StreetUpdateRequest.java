package ru.database.coursework.api.street.model;

public record StreetUpdateRequest(
        int id,

        String streetName,

        int cityId,

        boolean delete
) {


}
