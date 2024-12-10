package ru.database.coursework.api.bank.model;

public record BankUpdateRequest(
        int id,
        String bankName,
        String taxpayerIdentificationNumber,
        String bic,
        boolean delete
) {

}
