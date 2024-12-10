package ru.database.coursework.api.city.model;

public record CityUpdateRequest(
        int id,

        String cityName,

        boolean delete
) {


}
