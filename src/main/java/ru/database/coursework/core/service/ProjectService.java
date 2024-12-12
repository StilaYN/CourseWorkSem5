package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.project.model.Project;
import ru.database.coursework.api.project.model.ProjectCreationRequest;
import ru.database.coursework.api.project.model.ProjectFilter;
import ru.database.coursework.api.project.model.ProjectUpdateRequest;
import ru.database.coursework.core.repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public void createProject(ProjectCreationRequest project) {
        projectRepository.create(project);
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id);
    }

    public List<Project> getAllProjects(ProjectFilter projectFilter) {
        String template = (projectFilter == null || projectFilter.template() == null) ?
                null : "%" + projectFilter.template() + "%";
        return projectRepository.findAll(template);
    }

    public void updateProject(ProjectUpdateRequest updateProject) {
        projectRepository.update(updateProject);
    }

    public void deleteProject(int id) {
        projectRepository.delete(id);
    }

}
