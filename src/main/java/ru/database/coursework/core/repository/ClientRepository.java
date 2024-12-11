package ru.database.coursework.core.repository;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import ru.database.coursework.api.client.model.Client;
import ru.database.coursework.api.client.model.ClientCreationRequest;
import ru.database.coursework.api.client.model.ClientUpdateRequest;

import java.util.List;

@RegisterConstructorMapper(Client.class)
public interface ClientRepository {

    @SqlUpdate("""
            INSERT INTO clients (client_name, representative_first_name, representative_last_name, representative_middle_name,
                                 representative_phone_number, city_id, street_id, house_number, apartment_number, account_number,
                                 taxpayer_identification_number, bank_id)
            VALUES (:client.clientName, :client.representativeFirstName, :client.representativeLastName,
                    :client.representativeMiddleName, :client.representativePhoneNumber, :client.cityId, :client.streetId,
                    :client.houseNumber, :client.apartmentNumber, :client.accountNumber, :client.taxpayerIdentificationNumber,
                    :client.bankId);
            """)
    void save(@BindMethods("client") ClientCreationRequest clientCreationRequest);

    @SqlQuery("""
            SELECT clients.id,
                   client_name,
                   representative_first_name,
                   representative_last_name,
                   representative_middle_name,
                   representative_phone_number,
                   clients.city_id,
                   city_name,
                   clients.street_id,
                   street_name,
                   house_number,
                   apartment_number,
                   account_number,
                   clients.taxpayer_identification_number,
                   clients.bank_id,
                   bank_name
            FROM clients
                     JOIN public.banks b on b.id = clients.bank_id
                     JOIN public.cities c on c.id = clients.city_id
                     JOIN public.streets s on c.id = s.city_id
            WHERE clients.id=:id;
            """)
    Client findById(@Bind("id") Integer id);

    @SqlQuery("""
            SELECT clients.id,
                   client_name,
                   representative_first_name,
                   representative_last_name,
                   representative_middle_name,
                   representative_phone_number,
                   clients.city_id,
                   city_name,
                   clients.street_id,
                   street_name,
                   house_number,
                   apartment_number,
                   account_number,
                   clients.taxpayer_identification_number,
                   clients.bank_id,
                   bank_name
            FROM clients
                     JOIN public.banks b on b.id = clients.bank_id
                     JOIN public.cities c on c.id = clients.city_id
                     JOIN public.streets s on c.id = s.city_id
            WHERE client_name ILIKE coalesce(:template, client_name)
               OR clients.representative_first_name ILIKE coalesce(:template, representative_first_name)
               OR representative_last_name ILIKE coalesce(:template, representative_last_name)
               OR representative_middle_name ILIKE coalesce(:template, representative_last_name)
               OR representative_phone_number ILIKE coalesce(:template, representative_phone_number)
               OR city_name ILIKE coalesce(:template, city_name)
               OR street_name ILIKE coalesce(:template, street_name)
               OR house_number ILIKE coalesce(:template, house_number)
               OR apartment_number ILIKE coalesce(:template, apartment_number)
               OR account_number ILIKE coalesce(:template, account_number)
               OR clients.taxpayer_identification_number ILIKE coalesce(:template, clients.taxpayer_identification_number)
               OR bank_name ILIKE coalesce(:template, bank_name);
            """)
    List<Client> findAll(@Bind("template") String template);

    @SqlUpdate("""
            UPDATE clients
            SET client_name                   = :client.clientName,
                representative_first_name=:client.representativeFirstName,
                representative_last_name=:client.representativeLastName,
                representative_middle_name=:client.representativeMiddleName,
                representative_phone_number=:client.representativePhoneNumber,
                city_id=:client.cityId,
                street_id=:client.streetId,
                house_number=:client.houseNumber,
                apartment_number=:client.apartmentNumber,
                account_number=:client.accountNumber,
                taxpayer_identification_number=:client.taxpayerIdentificationNumber,
                bank_id=:client.bankId WHERE id=:client.id;
            """)
    void update(@BindMethods("client") ClientUpdateRequest client);

    @SqlUpdate("""
            DELETE FROM clients WHERE id=:id;
            """)
    void delete(@Bind("id") int id);

}
