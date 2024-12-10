package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.city.model.City;
import ru.database.coursework.api.city.model.CityCreationRequest;
import ru.database.coursework.api.city.model.CityFilter;
import ru.database.coursework.api.city.model.CityUpdateRequest;
import ru.database.coursework.core.repository.CityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public void createCity(CityCreationRequest city) {
        cityRepository.save(city);
    }

    public City getCityById(int id) {
        return cityRepository.findById(id);
    }

    public List<City> getAllCities(CityFilter cityFilter) {
        String cityName = (cityFilter == null || cityFilter.cityName() == null) ? null : "%" + cityFilter.cityName() + "%";
        return cityRepository.findAll(cityName);
    }

    public void updateCity(CityUpdateRequest city) {
        cityRepository.update(city);
    }

    public void deleteCityById(int id) {
        cityRepository.delete(id);
    }
}
