package com.projecti.projectintegrer;

import java.util.HashMap;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.projecti.projectintegrer.Crud.ClientService;
import com.projecti.projectintegrer.domain.entities.Client;

public class ClientServiceImpl implements ClientService{

    private final HashMap<String, Client> userMap = new HashMap<>();

    @Override
    public Client getClientById(String clientId) {
        return userMap.get(clientId);
        throw new UnsupportedOperationException("Unimplemented method 'getClientById'");
    }

    @Override
    public List<Client> getAllClients() {
        return List.copyOf(userMap.values());
        throw new UnsupportedOperationException("Unimplemented method 'getAllClients'");
    }
    @Override
    public void deleteClient(String clientId) {
        userMap.remove(clientId);
    }
    @Override
    public void createClient(Client client) {
        userMap.put(client.getClientById(), client);
        throw new UnsupportedOperationException("Unimplemented method 'createClient'");
    }
    @Override
    public void updateClient(String clientId, Client updatedclient) {
        if (userMap.containsKey(clientId)) {
            userMap.put(clientId, updatedclient);
        } else {
            // Handle the case where the user with the given ID doesn't exist
            throw new IllegalArgumentException("User not found with ID: " + clientId);
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }
}