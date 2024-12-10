package ru.database.coursework.core.Entity;

public record UserPrivileges(
        int menuId,
        String name,
        boolean read,
        boolean write,
        boolean edit,
        boolean delete
) {

}
