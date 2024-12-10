package ru.database.coursework.api.bank.model;

public record BankCreationRequest(
        String bankName,
        String taxpayerIdentificationNumber,
        String bic
) {

}
