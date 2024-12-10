package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.street.model.Street;
import ru.database.coursework.api.street.model.StreetCreationRequest;
import ru.database.coursework.api.street.model.StreetCreationResponse;
import ru.database.coursework.api.street.model.StreetFilter;
import ru.database.coursework.api.street.model.StreetUpdateRequest;
import ru.database.coursework.core.repository.CityRepository;
import ru.database.coursework.core.repository.StreetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StreetService {

    private final CityRepository cityRepository;

    private final StreetRepository streetRepository;

    public StreetCreationResponse getStreetCreationResponse() {
        return new StreetCreationResponse(cityRepository.findAll(null));
    }

    public void createStreet(StreetCreationRequest streetCreationRequest) {
        streetRepository.save(streetCreationRequest);
    }

    public List<Street> getStreets(StreetFilter filter) {
        String template = (filter == null || filter.template() == null) ? null : "%" + filter.template() + "%";
        return streetRepository.findAll(template);
    }

    public Street getStreet(int id) {
        return streetRepository.findById(id);
    }

    public void updateStreet(StreetUpdateRequest streetUpdateRequest) {
        streetRepository.update(streetUpdateRequest);
    }

    public void deleteStreet(int id){
        streetRepository.delete(id);
    }
}
