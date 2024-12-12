package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.team_member.model.TeamMember;
import ru.database.coursework.api.team_member.model.TeamMemberCreationRequest;
import ru.database.coursework.api.team_member.model.TeamMemberFilter;
import ru.database.coursework.api.team_member.model.TeamMemberUpdateRequest;
import ru.database.coursework.core.repository.TeamMemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;

    public void createTeamMember(TeamMemberCreationRequest teamMemberCreationRequest) {
        teamMemberRepository.save(teamMemberCreationRequest);
    }

    public TeamMember getTeamMemberById(int id) {
        return teamMemberRepository.findById(id);
    }

    public List<TeamMember> getAllTeamMember(TeamMemberFilter teamMemberFilter) {
        String template = (teamMemberFilter == null || teamMemberFilter.template() == null) ? null : "%" + teamMemberFilter.template() + "%";
        return teamMemberRepository.findAll(template);
    }

    public void updateTeamMember(TeamMemberUpdateRequest teamMemberUpdateRequest) {
        teamMemberRepository.update(teamMemberUpdateRequest);
    }

    public void deleteTeamMember(int id) {
        teamMemberRepository.delete(id);
    }

}
