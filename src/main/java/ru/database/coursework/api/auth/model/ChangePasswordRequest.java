package ru.database.coursework.api.auth.model;

public record ChangePasswordRequest(
        String oldPassword,
        String newPassword,
        String newPasswordRetry
) {

}
