package ru.database.coursework.api.project;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.project.model.ProjectCreationRequest;
import ru.database.coursework.api.project.model.ProjectFilter;
import ru.database.coursework.api.project.model.ProjectUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.ClientService;
import ru.database.coursework.core.service.EmployeeService;
import ru.database.coursework.core.service.ProjectService;

@Controller
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    private final EmployeeService employeeService;

    private final ClientService clientService;

    @PostMapping(ApiPaths.PROJECT_CREATE)
    public String createProject(ProjectCreationRequest projectCreationRequest) {
        projectService.createProject(projectCreationRequest);
        return "redirect:" + ApiPaths.PROJECT_LIST;
    }

    @GetMapping(ApiPaths.PROJECT_CREATE)
    public String getCreateProjectPage(Model model) {
        model.addAttribute("employeeList", employeeService.getAllEmployees(null));
        model.addAttribute("clientList", clientService.getAllClients(null));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.projects")));
        return "project/create";
    }

    @GetMapping(ApiPaths.PROJECT_LIST)
    public String getProjectList(Model model, ProjectFilter projectFilter) {
        model.addAttribute("projectList", projectService.getAllProjects(projectFilter));
        model.addAttribute("searchResult", projectFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.projects")));
        return "project/list";
    }

    @PostMapping(ApiPaths.PROJECT_UPDATE)
    public String updateProject(ProjectUpdateRequest projectUpdateRequest) {
        if(projectUpdateRequest.delete()){
            projectService.deleteProject(projectUpdateRequest.id());
        } else {
            projectService.updateProject(projectUpdateRequest);
        }
        return "redirect:" + ApiPaths.PROJECT_LIST;
    }

    @GetMapping(ApiPaths.PROJECT_UPDATE)
    public String getUpdateProjectPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("employeeList", employeeService.getAllEmployees(null));
        model.addAttribute("clientList", clientService.getAllClients(null));
        model.addAttribute("project", projectService.getProjectById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.projects")));
        return "project/update";
    }

}
