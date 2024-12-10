package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.street.model.Street;
import ru.database.coursework.api.street.model.StreetCreationRequest;
import ru.database.coursework.api.street.model.StreetUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(Street.class)
public interface StreetRepository {

    @SqlUpdate("""
            INSERT INTO streets (street_name, city_id) VALUES (:streets.streetName, :streets.cityId);
            """)
    void save(@BindMethods("streets")StreetCreationRequest request);

    @SqlQuery("""
            SELECT streets.id, street_name, city_id, city_name
            FROM streets
                     JOIN public.cities c on c.id = streets.city_id
            WHERE city_name ILIKE coalesce(:template, city_name)
               OR street_name ILIKE coalesce(:template, street_name) ORDER BY streets.id; 
            """)
    List<Street> findAll(@Bind("template") String template);

    @SqlQuery("""
            SELECT streets.id, street_name, city_id, city_name
            FROM streets
                     JOIN public.cities c on c.id = streets.city_id
            WHERE streets.id=:id
            """)
    Street findById(@Bind("id") int id);

    @SqlUpdate("""
            UPDATE streets SET street_name=:street.streetName, city_id=:streets.cityId WHERE id=:street.id; 
            """)
    void update(@BindMethods("street") StreetUpdateRequest streetUpdateRequest);

    @SqlUpdate("""
            DELETE FROM streets WHERE id=:id;
            """)
    void delete(@Bind("id") int id);
}
