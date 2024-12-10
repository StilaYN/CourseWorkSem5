package ru.database.coursework.core.config;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.database.coursework.core.repository.AuthRepository;
import ru.database.coursework.core.repository.BankRepository;
import ru.database.coursework.core.repository.CityRepository;
import ru.database.coursework.core.repository.DocumentTypeRepository;
import ru.database.coursework.core.repository.EmployeeRepository;
import ru.database.coursework.core.repository.MenuRepository;
import ru.database.coursework.core.repository.SpecializationRepository;
import ru.database.coursework.core.repository.StreetRepository;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        return Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new PostgresPlugin());
    }

    @Bean
    public EmployeeRepository employeeRepository(Jdbi jdbi) {return jdbi.onDemand(EmployeeRepository.class);}

    @Bean
    public CityRepository cityRepository(Jdbi jdbi) {return jdbi.onDemand(CityRepository.class);}

    @Bean
    public MenuRepository menuRepository(Jdbi jdbi) {return jdbi.onDemand(MenuRepository.class);}

    @Bean
    public AuthRepository authRepository(Jdbi jdbi) {return jdbi.onDemand(AuthRepository.class);}

    @Bean
    public StreetRepository streetRepository(Jdbi jdbi) {return jdbi.onDemand(StreetRepository.class);}

    @Bean
    public BankRepository bankRepository(Jdbi jdbi) {return jdbi.onDemand(BankRepository.class);}

    @Bean
    public DocumentTypeRepository documentTypeRepository(Jdbi jdbi) {return jdbi.onDemand(DocumentTypeRepository.class);}

    @Bean
    public SpecializationRepository specializationRepository(Jdbi jdbi) {return jdbi.onDemand(SpecializationRepository.class);}
}
