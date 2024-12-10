package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.specialization.model.Specialization;
import ru.database.coursework.api.specialization.model.SpecializationCreationRequest;
import ru.database.coursework.api.specialization.model.SpecializationUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(Specialization.class)
public interface SpecializationRepository {
    @SqlUpdate("""
            INSERT INTO specializations (specialization_name) VALUES (:specialization.specializationName)
            """)
    void save(@BindMethods(value = "specialization")SpecializationCreationRequest specializationCreationRequest);

    @SqlQuery("""
            SELECT * FROM specializations WHERE id = :specialization_id;
            """)
    Specialization findById(@Bind("specialization_id") int specializationId);

    @SqlQuery("""
            SELECT * FROM specializations WHERE specialization_name ILIKE coalesce(:specialization_name, specialization_name) ORDER BY id
            """)
    List<Specialization> findAll(@Bind("specialization_name") String specializationName);

    @SqlUpdate("""
            UPDATE specializations SET specialization_name=:specialization.specializationName WHERE id=:specialization.id;
            """)
    void update(@BindMethods("specialization") SpecializationUpdateRequest specialization);

    @SqlUpdate("""
            DELETE FROM specializations WHERE id = :id;
            """)
    void delete(@Bind("id") Integer id);
}
