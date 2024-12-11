package ru.database.coursework.api.client;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.database.coursework.api.ApiPaths;
import ru.database.coursework.api.client.model.ClientCreationRequest;
import ru.database.coursework.api.client.model.ClientFilter;
import ru.database.coursework.api.client.model.ClientUpdateRequest;
import ru.database.coursework.core.Context;
import ru.database.coursework.core.service.BankService;
import ru.database.coursework.core.service.CityService;
import ru.database.coursework.core.service.ClientService;
import ru.database.coursework.core.service.StreetService;

@Controller
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final CityService cityService;

    private final StreetService streetService;

    private final BankService bankService;

    @PostMapping(ApiPaths.CLIENT_CREATE)
    public String createClient(ClientCreationRequest createRequest) {
        clientService.createClient(createRequest);
        return "redirect:" + ApiPaths.CLIENT_LIST;
    }

    @GetMapping(ApiPaths.CLIENT_CREATE)
    public String getCreateClientPage(Model model) {
        model.addAttribute("cityList", cityService.getAllCities(null));
        model.addAttribute("streetList", streetService.getStreets(null));
        model.addAttribute("bankList", bankService.getAllBanks(null));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.clients")));
        return "client/create";
    }

    @GetMapping(ApiPaths.CLIENT_LIST)
    public String getClientList(Model model, ClientFilter clientFilter) {
        model.addAttribute("clientList", clientService.getAllClients(clientFilter));
        model.addAttribute("searchResult", clientFilter.template());
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.clients")));
        return "client/list";
    }

    @PostMapping(ApiPaths.CLIENT_UPDATE)
    public String updateClient(ClientUpdateRequest updateRequest) {
        if(updateRequest.delete()){
            clientService.deleteClient(updateRequest.id());
        } else {
            clientService.updateClient(updateRequest);
        }
        return "redirect:" + ApiPaths.CLIENT_LIST;
    }

    @GetMapping(ApiPaths.CLIENT_UPDATE)
    public String getUpdateClientPage(Model model, @PathVariable("id") int id) {
        model.addAttribute("cityList", cityService.getAllCities(null));
        model.addAttribute("streetList", streetService.getStreets(null));
        model.addAttribute("bankList", bankService.getAllBanks(null));
        model.addAttribute("menu", Context.menu);
        model.addAttribute("authorityMap", Context.authorityMap.get(Context.tablesName.get("menu.clients")));
        model.addAttribute("client", clientService.getClientById(id));
        return "client/update";
    }
}
