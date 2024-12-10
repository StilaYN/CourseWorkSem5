package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.city.model.City;
import ru.database.coursework.api.city.model.CityCreationRequest;
import ru.database.coursework.api.city.model.CityUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(City.class)
public interface CityRepository {

    @SqlUpdate("""
            INSERT INTO cities (city_name) VALUES (:city.cityName)
            """)
    void save(@BindMethods(value = "city") CityCreationRequest city);

    @SqlQuery("""
            SELECT * FROM cities WHERE id = :city_id;
            """)
    City findById(@Bind("city_id") int cityId);

    @SqlQuery("""
            SELECT * FROM cities WHERE city_name ILIKE coalesce(:city_name, city_name) ORDER BY id
            """)
    List<City> findAll(@Bind("city_name") String cityName);

    @SqlUpdate("""
            UPDATE cities SET city_name=:city.cityName WHERE id=:city.id;
            """)
    void update(@BindMethods("city") CityUpdateRequest city);

    @SqlUpdate("""
            DELETE FROM cities WHERE id = :id;
            """)
    void delete(@Bind("id") Integer id);
}
