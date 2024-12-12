package ru.database.coursework.api.team.model;

public record TeamUpdateRequest(
        int id,
        int teamMemId,
        int projectId,
        boolean delete
) {

}
