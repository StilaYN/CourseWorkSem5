package ru.database.coursework.api.team.model;

public record TeamCreationRequest(
        int teamMemId,
        int projectId
) {

}
