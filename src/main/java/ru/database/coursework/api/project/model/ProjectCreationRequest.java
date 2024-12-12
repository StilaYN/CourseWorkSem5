package ru.database.coursework.api.project.model;

import java.sql.Date;

public record ProjectCreationRequest(
        String name,
        int price,
        int directorId,
        Date startDate,
        Date endDate,
        int clientId,
        int initialBid
) {

}
