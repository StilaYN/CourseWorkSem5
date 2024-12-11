package ru.database.coursework.api.client.model;

public record Client(
        int id,
        String clientName,
        String representativeLastName,
        String representativeFirstName,
        String representativeMiddleName,
        String representativePhoneNumber,
        int cityId,
        String cityName,
        int streetId,
        String streetName,
        String houseNumber,
        String apartmentNumber,
        String accountNumber,
        String taxpayerIdentificationNumber,
        int bankId,
        String bankName
) {

}
