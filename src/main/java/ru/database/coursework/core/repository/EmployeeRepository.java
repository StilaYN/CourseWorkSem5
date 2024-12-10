package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import ru.database.coursework.api.employee.model.Employee;
import ru.database.coursework.core.config.DateMapper;

import java.util.List;

@RegisterConstructorMapper(value = Employee.class)
@RegisterColumnMapper(DateMapper.class)
public interface EmployeeRepository {

    @SqlQuery("""
            SELECT employees.id,
                   last_name,
                   first_name,
                   middle_name,
                   sex,
                   birth_date,
                   city_name as city,
                   street_name as street,
                   house_number,
                   apartment_number,
                   specialization_name as specialization,
                   experience,
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
    Employee getEmployeeById(@Bind("id") int id);

    @SqlQuery("""
            SELECT employees.id,
                   last_name,
                   first_name,
                   middle_name,
                   sex,
                   birth_date,
                   city_name as city,
                   street_name as street,
                   house_number,
                   apartment_number,
                   specialization_name as specialization,
                   experience,
                   document_type_name as document_type,
                   document_number,
                   education,
                   salary
            FROM employees
                     JOIN public.cities c on c.id = employees.city_id
                     JOIN public.streets s on s.id = employees.street_id
                     JOIN specializations sp on employees.specialization_id = sp.id
                     JOIN document_types d on employees.document_type_id = d.id
            ORDER BY id;
            """)
    List<Employee> getEmployees();

}
