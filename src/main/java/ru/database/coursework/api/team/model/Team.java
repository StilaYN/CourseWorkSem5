package ru.database.coursework.api.team.model;

public record Team(
        int id,
        String employeeName,
        int teamMemId,
        String name,
        int projectId
) {

}
