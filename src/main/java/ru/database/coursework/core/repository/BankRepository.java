package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.bank.model.Bank;
import ru.database.coursework.api.bank.model.BankCreationRequest;
import ru.database.coursework.api.bank.model.BankUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(Bank.class)
public interface BankRepository {

    @SqlUpdate("""
            INSERT INTO banks (bank_name, taxpayer_identification_number, bic)
            VALUES (:bank.bankName, :bank.taxpayerIdentificationNumber, :bank.bic);
            """)
    void save(@BindMethods(value = "bank") BankCreationRequest bank);

    @SqlQuery("""
            SELECT * FROM banks WHERE id = :bank_id;
            """)
    Bank findById(@Bind("bank_id") int bankId);

    @SqlQuery("""
            SELECT *
            FROM banks 
            WHERE bank_name ILIKE coalesce(:template, bank_name)
            OR taxpayer_identification_number ILIKE coalesce(:template, taxpayer_identification_number)
            OR bic ILIKE coalesce(:template, bic)
            ORDER BY id;
            """)
    List<Bank> findAll(@Bind("template") String template);

    @SqlUpdate("""
            UPDATE banks SET bank_name=:bank.bankName, taxpayer_identification_number=:bank.taxpayerIdentificationNumber,
            bic=:bank.bic
            WHERE id=:bank.id;
            """)
    void update(@BindMethods("bank") BankUpdateRequest bank);

    @SqlUpdate("""
            DELETE FROM banks WHERE id = :id;
            """)
    void delete(@Bind("id") Integer id);

}
