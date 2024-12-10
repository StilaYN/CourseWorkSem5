package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.specialization.model.Specialization;
import ru.database.coursework.api.specialization.model.SpecializationCreationRequest;
import ru.database.coursework.api.specialization.model.SpecializationFilter;
import ru.database.coursework.api.specialization.model.SpecializationUpdateRequest;
import ru.database.coursework.core.repository.SpecializationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecializationService {

    public final SpecializationRepository specializationRepository;

    public void createSpecialization(SpecializationCreationRequest specialization) {
        specializationRepository.save(specialization);
    }

    public Specialization getSpecializationById(int id) {
        return specializationRepository.findById(id);
    }

    public List<Specialization> getAllSpecialization(SpecializationFilter specializationFilter) {
        String template = (specializationFilter == null || specializationFilter.template() == null) ?
                null : "%" + specializationFilter.template() + "%";
        return specializationRepository.findAll(template);
    }

    public void updateSpecialization(SpecializationUpdateRequest specialization) {
        specializationRepository.update(specialization);
    }

    public void deleteSpecialization(int id) {
        specializationRepository.delete(id);
    }
}
