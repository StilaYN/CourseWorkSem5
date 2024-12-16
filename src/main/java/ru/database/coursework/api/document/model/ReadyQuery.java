package ru.database.coursework.api.document.model;

import lombok.Getter;

import java.util.List;

@Getter
public enum ReadyQuery {

    SALARY(
            "salary",
            """
            SELECT employees.id,
                   last_name,
                   first_name,
                   middle_name,
                   specialization_name as specialization,
                   salary
            FROM employees
                     JOIN specializations sp on employees.specialization_id = sp.id
            ORDER BY employees.id;
            """
    ),
    CLIENTS(
            "client",
            """
            SELECT clients.id,
                   client_name,
                   representative_first_name,
                   representative_last_name,
                   representative_middle_name,
                   representative_phone_number,
                   apartment_number,
                   account_number,
                   bank_name
            FROM clients
                     JOIN public.banks b on b.id = clients.bank_id
            """
    )

    ;

    public static final List<ReadyQuery> readyQueries = List.of(SALARY, CLIENTS);

    private final String name;
    private final String query;

    ReadyQuery(String name, String query) {
        this.name = name;
        this.query = query;
    }


}
