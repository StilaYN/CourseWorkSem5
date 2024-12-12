package ru.database.coursework.api.project.model;

import java.sql.Date;

public record Project(
    int id,
    String name,
    int price,
    int directorId,
    String directorName,
    Date startDate,
    Date endDate,
    int clientId,
    String clientName,
    int initialBid
) {

}
