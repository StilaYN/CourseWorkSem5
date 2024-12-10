package ru.database.coursework.api.document_type.model;

public record DocumentTypeUpdateRequest(
        int id,
        String documentTypeName,
        boolean delete
) {

}
