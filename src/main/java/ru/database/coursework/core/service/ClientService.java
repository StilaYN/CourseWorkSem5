package ru.database.coursework.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.database.coursework.api.client.model.Client;
import ru.database.coursework.api.client.model.ClientCreationRequest;
import ru.database.coursework.api.client.model.ClientFilter;
import ru.database.coursework.api.client.model.ClientUpdateRequest;
import ru.database.coursework.core.repository.ClientRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void createClient(ClientCreationRequest request){
        clientRepository.save(request);
    }

    public Client getClientById(int id){
        return clientRepository.findById(id);
    }

    public List<Client> getAllClients(ClientFilter clientFilter){
        String template = (clientFilter == null || clientFilter.template() == null) ?
                null : "%" + clientFilter.template() + "%";
        return clientRepository.findAll(template);
    }

    public void updateClient(ClientUpdateRequest client){
        clientRepository.update(client);
    }

    public void deleteClient(int id){
        clientRepository.delete(id);
    }
}
