package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.team_member.model.TeamMember;
import ru.database.coursework.api.team_member.model.TeamMemberCreationRequest;
import ru.database.coursework.api.team_member.model.TeamMemberUpdateRequest;
import ru.database.coursework.core.config.DateMapper;

import java.util.List;

@RegisterConstructorMapper(TeamMember.class)
@RegisterColumnMapper(DateMapper.class)
public interface TeamMemberRepository {

    @SqlUpdate("""
            INSERT INTO team_members(employee_id, start_date, end_date)
            VALUES (:teamMember.employeeId, :teamMember.startDate, :teamMember.endDate);
            """)
    void save(@BindMethods("teamMember") TeamMemberCreationRequest teamMemberCreationRequest);

    @SqlQuery("""
            SELECT team_members.id,concat_ws(' ', last_name, first_name, middle_name) as employeeName, employee_id, start_date, end_date
            FROM team_members
                     JOIN public.employees e on team_members.employee_id = e.id
            WHERE team_members.id = :id;
            """)
    TeamMember findById(@Bind("id") int id);

    @SqlQuery("""
            SELECT team_members.id,
                   concat_ws(' ', last_name, first_name, middle_name) as employeeName,
                   employee_id,
                   start_date,
                   end_date
            FROM team_members
                     JOIN public.employees e on team_members.employee_id = e.id
            WHERE concat_ws(' ', last_name, first_name, middle_name) ILIKE
                  coalesce(:template, concat_ws(' ', last_name, first_name, middle_name))
            ORDER BY team_members.id;
            """)
    List<TeamMember> findAll(@Bind("template") String template);

    @SqlUpdate("""
            UPDATE team_members
            SET employee_id=:teamMember.employeeId,
                start_date=:teamMember.startDate,
                end_date=:teamMember.endDate
            WHERE id=:teamMember.id;
            """)
    void update(@BindMethods("teamMember") TeamMemberUpdateRequest project);

    @SqlUpdate("""
            DELETE FROM team_members WHERE id=:id;
            """)
    void delete(@Bind("id") int id);

}
