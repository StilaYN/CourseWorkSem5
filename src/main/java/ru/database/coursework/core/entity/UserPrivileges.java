package ru.database.coursework.core.entity;

public record UserPrivileges(
        int menuId,
        String name,
        boolean read,
        boolean write,
        boolean edit,
        boolean delete
) {

}
