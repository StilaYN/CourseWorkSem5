package ru.database.coursework.api.bank.model;

public record Bank(
        int id,
        String bankName,
        String taxpayerIdentificationNumber,
        String bic
) {

}
