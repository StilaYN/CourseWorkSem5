package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.bank.model.Bank;
import ru.database.coursework.api.bank.model.BankCreationRequest;
import ru.database.coursework.api.bank.model.BankFilter;
import ru.database.coursework.api.bank.model.BankUpdateRequest;
import ru.database.coursework.core.repository.BankRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {
    private final BankRepository bankRepository;

    public void createBank(BankCreationRequest bank) {
        bankRepository.save(bank);
    }

    public Bank getBankById(int id) {
        return bankRepository.findById(id);
    }

    public List<Bank> getAllBanks(BankFilter bankFilter) {
        String template = (bankFilter == null || bankFilter.template() == null) ? null : "%" + bankFilter.template() + "%";
        return bankRepository.findAll(template);
    }

    public void updateBank(BankUpdateRequest bank) {
        bankRepository.update(bank);
    }

    public void deleteBank(int id) {
        bankRepository.delete(id);
    }
}
