package ru.database.coursework.api.client.model;

public record ClientCreationRequest(
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
    int bankId
) {

}
