package ru.database.coursework.core.entity;


public record MenuItemEntity(
        int id,
        String displayLabel,
        String actionUri
) {

}
