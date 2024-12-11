package ru.database.coursework.api.client.model;

public record ClientUpdateRequest(
        int id,
        String clientName,
        String representativeFirstName,
        String representativeLastName,
        String representativeMiddleName,
        String representativePhoneNumber,
        int cityId,
        int streetId,
        String houseNumber,
        String apartmentNumber,
        String accountNumber,
        String taxpayerIdentificationNumber,
        int bankId,
        boolean delete
) {

}
