package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.team.model.Team;
import ru.database.coursework.api.team.model.TeamCreationRequest;
import ru.database.coursework.api.team.model.TeamUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(Team.class)
public interface TeamRepository {

    @SqlUpdate("""
            INSERT INTO teams (team_mem_id, project_id) VALUES (:team.teamMemId, :team.projectId);
            """)
    void save(@BindMethods("team") TeamCreationRequest teamCreationRequest);

    @SqlQuery("""
            SELECT teams.id,
                team_mem_id,
                concat_ws(' ', last_name, first_name, middle_name) as employee_name,
                name,
                project_id
            FROM teams
                JOIN public.projects p on p.id = teams.project_id
                JOIN public.team_members tm on tm.id = teams.team_mem_id
                JOIN public.employees e on e.id = tm.employee_id
            WHERE teams.id=:id;
            """)
    Team findById(@Bind("id") int id);

    @SqlQuery("""
            SELECT teams.id,
                   team_mem_id,
                   concat_ws(' ', last_name, first_name, middle_name) as employee_name,
                   name,
                   project_id
            FROM teams
                     JOIN public.projects p on p.id = teams.project_id
                     JOIN public.team_members tm on tm.id = teams.team_mem_id
                     JOIN public.employees e on e.id = tm.employee_id
            WHERE concat_ws(' ', last_name, first_name, middle_name) ILIKE
                  coalesce(:template, concat_ws(' ', last_name, first_name, middle_name))
               OR name ILIKE coalesce(:template, name)
            ORDER BY id;
            """)
    List<Team> findAll(@Bind("template") String template);

    @SqlUpdate("""
            UPDATE teams SET team_mem_id=:team.teamMemId, project_id=:team.projectId WHERE id=:team.id;
            """)
    void update(@BindMethods("team") TeamUpdateRequest project);

    @SqlUpdate("""
            DELETE FROM teams WHERE id=:id;
            """)
    void delete(@Bind("id") int id);
}
