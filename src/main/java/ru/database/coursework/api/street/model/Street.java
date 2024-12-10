package ru.database.coursework.api.street.model;

public record Street(
        int id,

        String streetName,

        int cityId,

        String cityName
) {

}
