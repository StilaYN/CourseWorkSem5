package ru.database.coursework.api.team;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.team.model.TeamCreationRequest;
import ru.database.coursework.api.team.model.TeamFilter;
import ru.database.coursework.api.team.model.TeamUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.ProjectService;
import ru.database.coursework.core.service.TeamMemberService;
import ru.database.coursework.core.service.TeamService;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    private final TeamMemberService teamMemberService;

    private final ProjectService projectService;

    @PostMapping(ApiPaths.TEAM_CREATE)
    public String createTeam(TeamCreationRequest teamCreationRequest) {
        teamService.createTeam(teamCreationRequest);
        return "redirect:" + ApiPaths.TEAM_LIST;
    }

    @GetMapping(ApiPaths.TEAM_CREATE)
    public String getCreateTeamPage(Model model) {
        model.addAttribute("teamMemList", teamMemberService.getAllTeamMember(null));
        model.addAttribute("projectList", projectService.getAllProjects(null));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.teams")));
        return "team/create";
    }

    @GetMapping(ApiPaths.TEAM_LIST)
    public String getTeamList(Model model, TeamFilter teamMember) {
        model.addAttribute("teamList", teamService.getAllTeam(teamMember));
        model.addAttribute("searchResult", teamMember.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.teams")));
        return "team/list";
    }

    @PostMapping(ApiPaths.TEAM_UPDATE)
    public String updateTeam(TeamUpdateRequest teamMemberRequest) {
        if(teamMemberRequest.delete()){
            teamService.deleteTeam(teamMemberRequest.id());
        } else {
            teamService.updateTeam(teamMemberRequest);
        }
        return "redirect:" + ApiPaths.TEAM_LIST;
    }

    @GetMapping(ApiPaths.TEAM_UPDATE)
    public String getTeamProjectPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("teamMemList", teamMemberService.getAllTeamMember(null));
        model.addAttribute("projectList", projectService.getAllProjects(null));
        model.addAttribute("team", teamService.getTeamById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.teams")));
        return "team/update";
    }

}
