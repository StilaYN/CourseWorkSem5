package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.project.model.Project;
import ru.database.coursework.api.project.model.ProjectCreationRequest;
import ru.database.coursework.api.project.model.ProjectUpdateRequest;
import ru.database.coursework.core.config.DateMapper;

import java.util.List;

@RegisterConstructorMapper(Project.class)
@RegisterColumnMapper(DateMapper.class)
public interface ProjectRepository {

    @SqlUpdate("""
            INSERT INTO projects (name, price, director_id, start_date, end_date, client_id, initial_bid)
            VALUES (project:name, project:price, project:directorId, project:startDate, project:endDate, project:clientId,
                    project:initialBid);
            """)
    void save(@BindMethods("project") ProjectCreationRequest projectCreationRequest);

    @SqlQuery("""
            SELECT projects.id,
                   name,
                   price,
                   concat_ws(' ', last_name, first_name, middle_name) as directorName,
                   director_id,
                   start_date,
                   end_date,
                   client_name,
                   client_id,
                   initial_bid
            FROM projects
                     JOIN public.employees e on e.id = projects.director_id
                     JOIN public.clients c on projects.client_id = c.id
            WHERE projects.id=:id;
            """)
    Project findById(@Bind("id") int id);

    @SqlQuery("""
            SELECT projects.id,
                   name,
                   price,
                   concat_ws(' ', last_name, first_name, middle_name) as director_name,
                   director_id,
                   start_date,
                   end_date,
                   client_name,
                   client_id,
                   initial_bid
            FROM projects
                     JOIN public.employees e on e.id = projects.director_id
                     JOIN public.clients c on projects.client_id = c.id
            WHERE name ILIKE coalesce(:template, name)
               OR concat_ws(' ', last_name, first_name, middle_name) ILIKE
                  coalesce(:template, concat_ws(' ', last_name, first_name, middle_name))
               OR client_name ILIKE coalesce(:template, client_name)
            ORDER BY projects.id;
            """)
    List<Project> findAll(@Bind("template") String template);

    @SqlUpdate("""
            UPDATE projects
            SET name=:project.name,
                price=:project.price,
                director_id=:project.directorId,
                start_date=:project.startDate,
                end_date=:project.endDate,
                client_id=:project.clientId,
                initial_bid=:project.initialBid
            WHERE id=:project.id;
            """)
    void update(@BindMethods("project") ProjectUpdateRequest project);

    @SqlUpdate("""
            DELETE FROM projects WHERE id=:id;
            """)
    void delete(@Bind("id") int id);
}
