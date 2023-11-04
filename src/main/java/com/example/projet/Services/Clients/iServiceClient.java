package com.example.projet.Services.Clients;

import com.example.projet.Model.Client;

import java.util.List;

public interface iServiceClient {
    Client saveClient(Client client);
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client updateClient(Long id, Client newUser);
    String deleteClient(Long id);
    Client registerClient(Client client);
    Client loginClient(String username, String password);

//    List<Client> getAllUsers();
//    Client newUser(Client utilisateur);
//    Optional<Client> getUserById(Long id);
//    Client updateUser(Client utilisateur,Long id);

}
