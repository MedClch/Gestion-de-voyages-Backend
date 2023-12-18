package com.example.projet.Services.Clients;

import com.example.projet.DTO.ClientDTO;

import java.util.List;

public interface iServiceClient {
//    Client saveClient(Client client);
    ClientDTO saveClient(ClientDTO clientDTO);
//    List<Client> getAllClients();
    List<ClientDTO> getClients();
//    Client getClientById(Long id);
    ClientDTO getClientById(Long id);
    ClientDTO updateClient(Long id, ClientDTO newUserDTO);
    String deleteClient(Long id);
    ClientDTO registerClient(ClientDTO clientDTO);
    ClientDTO loginClient(String username, String password);
}
