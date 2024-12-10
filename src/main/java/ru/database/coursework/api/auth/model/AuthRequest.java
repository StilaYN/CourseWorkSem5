package ru.database.coursework.api.auth.model;

public record AuthRequest(
        String username,

        String password
) {

}
