package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.employee.model.Employee;
import ru.database.coursework.api.employee.model.EmployeeCreationRequest;
import ru.database.coursework.api.employee.model.EmployeeUpdateRequest;
import ru.database.coursework.core.config.DateMapper;

import java.util.List;

@RegisterConstructorMapper(value = Employee.class)
@RegisterColumnMapper(DateMapper.class)
public interface EmployeeRepository {

    @SqlUpdate("""
            INSERT INTO employees (first_name, last_name, middle_name, sex, birth_date, city_id, street_id, house_number,
                                   apartment_number, specialization_id, experience, document_type_id, document_number, education,
                                   salary)
            VALUES (:employee.firstName, :employee.lastName, :employee.middleName, :employee.sex, :employee.birthDate,
                    :employee.cityId, :employee.streetId, :employee.houseNumber, :employee.apartmentNumber,
                    :employee.specializationId, :employee.experience, :employee.documentTypeId, :employee.documentNumber,
                    :employee.education, :employee.salary)
            """)
    void save(@BindMethods("employee")EmployeeCreationRequest employee);

    @SqlQuery("""
            SELECT employees.id,
                   last_name,
                   first_name,
                   middle_name,
                   sex,
                   birth_date,
                   employees.city_id,
                   city_name as city,
                   employees.street_id,
                   street_name as street,
                   house_number,
                   apartment_number,
                   employees.specialization_id,
                   specialization_name as specialization,
                   experience,
                   employees.document_type_id,
                   document_type_name as document_type,
                   document_number,
                   education,
                   salary
            FROM employees
                     JOIN public.cities c on c.id = employees.city_id
                     JOIN public.streets s on s.id = employees.street_id
                     JOIN specializations sp on employees.specialization_id = sp.id
                     JOIN document_types d on employees.document_type_id = d.id
            WHERE employees.id = :id;
            """)
    Employee findById(@Bind("id") int id);

    @SqlQuery("""
            SELECT employees.id,
                   last_name,
                   first_name,
                   middle_name,
                   sex,
                   birth_date,
                   employees.city_id,
                   city_name           as city,
                   employees.street_id,
                   street_name         as street,
                   house_number,
                   apartment_number,
                   employees.specialization_id,
                   specialization_name as specialization,
                   experience,
                   employees.document_type_id,
                   document_type_name  as document_type,
                   document_number,
                   education,
                   salary
            FROM employees
                     JOIN public.cities c on c.id = employees.city_id
                     JOIN public.streets s on s.id = employees.street_id
                     JOIN specializations sp on employees.specialization_id = sp.id
                     JOIN document_types d on employees.document_type_id = d.id
            WHERE last_name ILIKE coalesce(:template, last_name)
               OR first_name ILIKE coalesce(:template, first_name)
               OR middle_name ILIKE coalesce(:template, middle_name)
               OR sex::text ILIKE coalesce(:template, sex::text)
               OR city_name ILIKE coalesce(:template, city_name)
               OR street_name ILIKE coalesce(:template, street_name)
               OR house_number ILIKE coalesce( :template, house_number)
               OR apartment_number ILIKE coalesce(:template, apartment_number)
               OR specialization_name ILIKE coalesce(:template, specialization_name)
               OR experience ILIKE coalesce(:template, experience)
               OR document_type_name ILIKE coalesce(:template, document_type_name)
               OR document_number ILIKE coalesce(:template, document_number)
               OR education ILIKE coalesce(:template, education)
            ORDER BY id;
            """)
    List<Employee> findAll(@Bind("template") String template);

    @SqlUpdate("""
            UPDATE employees
            SET first_name=:employee.firstName,
                last_name=:employee.lastName,
                middle_name=:employee.middleName,
                sex=:employee.sex,
                birth_date=:employee.birthDate,
                city_id=:employee.cityId,
                street_id=:employee.streetId,
                house_number=:employee.houseNumber,
                apartment_number=:employee.apartmentNumber,
                specialization_id=:employee.specializationId,
                experience=:employee.experience,
                document_type_id=:employee.documentTypeId,
                document_number=:employee.documentNumber,
                education=:employee.education,
                salary=:employee.salary
            WHERE id=:employee.id
            """)
    void update(@BindMethods("employee") EmployeeUpdateRequest employee);

    @SqlUpdate("""
            DELETE FROM employees WHERE id=:id;
            """)
    void delete(@Bind("id") Integer id);
}
