package ru.database.coursework.api.team_member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.team_member.model.TeamMemberCreationRequest;
import ru.database.coursework.api.team_member.model.TeamMemberFilter;
import ru.database.coursework.api.team_member.model.TeamMemberUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.EmployeeService;
import ru.database.coursework.core.service.TeamMemberService;

@Controller
@RequiredArgsConstructor
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    private final EmployeeService employeeService;

    @PostMapping(ApiPaths.TEAM_MEMBER_CREATE)
    public String createProject(TeamMemberCreationRequest teamMemberCreationRequest) {
        teamMemberService.createTeamMember(teamMemberCreationRequest);
        return "redirect:" + ApiPaths.TEAM_MEMBER_LIST;
    }

    @GetMapping(ApiPaths.TEAM_MEMBER_CREATE)
    public String getCreateProjectPage(Model model) {
        model.addAttribute("employeeList", employeeService.getAllEmployees(null));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.teams.members")));
        return "team_member/create";
    }

    @GetMapping(ApiPaths.TEAM_MEMBER_LIST)
    public String getProjectList(Model model, TeamMemberFilter teamMember) {
        model.addAttribute("teamMemberList", teamMemberService.getAllTeamMember(teamMember));
        model.addAttribute("searchResult", teamMember.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.teams.members")));
        return "team_member/list";
    }

    @PostMapping(ApiPaths.TEAM_MEMBER_UPDATE)
    public String updateProject(TeamMemberUpdateRequest teamMemberUpdateRequest) {
        if(teamMemberUpdateRequest.delete()){
            teamMemberService.deleteTeamMember(teamMemberUpdateRequest.id());
        } else {
            teamMemberService.updateTeamMember(teamMemberUpdateRequest);
        }
        return "redirect:" + ApiPaths.TEAM_MEMBER_LIST;
    }

    @GetMapping(ApiPaths.TEAM_MEMBER_UPDATE)
    public String getUpdateProjectPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("employeeList", employeeService.getAllEmployees(null));
        model.addAttribute("teamMember", teamMemberService.getTeamMemberById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.teams.members")));
        return "team_member/update";
    }

}
