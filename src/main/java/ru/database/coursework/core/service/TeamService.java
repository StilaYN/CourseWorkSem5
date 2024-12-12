package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.team.model.Team;
import ru.database.coursework.api.team.model.TeamCreationRequest;
import ru.database.coursework.api.team.model.TeamFilter;
import ru.database.coursework.api.team.model.TeamUpdateRequest;
import ru.database.coursework.core.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public void createTeam(TeamCreationRequest teamCreationRequest) {
        teamRepository.save(teamCreationRequest);
    }

    public Team getTeamById(int id) {
        return teamRepository.findById(id);
    }

    public List<Team> getAllTeam(TeamFilter teamFilter) {
        String template = (teamFilter == null || teamFilter.template() == null) ? null : "%" + teamFilter.template() + "%";
        return teamRepository.findAll(template);
    }

    public void updateTeam(TeamUpdateRequest teamMemberUpdateRequest) {
        teamRepository.update(teamMemberUpdateRequest);
    }

    public void deleteTeam(int id) {
        teamRepository.delete(id);
    }

}
