package ru.database.coursework.api.street.model;

import ru.database.coursework.api.city.model.City;

import java.util.List;

public record StreetCreationResponse(
        List<City> cities
) {

}
