package ru.database.coursework.api.bank;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.bank.model.BankCreationRequest;
import ru.database.coursework.api.bank.model.BankFilter;
import ru.database.coursework.api.bank.model.BankUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.BankService;

@Controller
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping(ApiPaths.BANK_CREATE)
    public String createBank(BankCreationRequest bankCreationRequest) {
        bankService.createBank(bankCreationRequest);
        return "redirect:/bank/list";
    }

    @GetMapping(ApiPaths.BANK_CREATE)
    public String geCreateBankPage(Model model) {
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.banks")));
        return "bank/create";
    }

    @GetMapping(ApiPaths.BANK_LIST)
    public String getCityListPage(@ParameterObject BankFilter bankFilter, Model model) {
        model.addAttribute("bankList", bankService.getAllBanks(bankFilter));
        model.addAttribute("searchResult", bankFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.banks")));
        return "bank/list";
    }

    @PostMapping(ApiPaths.BANK_UPDATE)
    public String updateCity(BankUpdateRequest bankUpdateRequest) {
        if (bankUpdateRequest.delete()) {
            bankService.deleteBank(bankUpdateRequest.id());
        } else {
            bankService.updateBank(bankUpdateRequest);
        }
        return "redirect:/bank/list";
    }

    @GetMapping(ApiPaths.BANK_UPDATE)
    public String getUpdateCityPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("bank", bankService.getBankById(id));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.banks")));
        return "bank/update";
    }
}
