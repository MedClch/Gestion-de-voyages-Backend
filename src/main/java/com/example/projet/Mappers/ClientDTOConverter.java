package com.example.projet.Mappers;

import com.example.projet.DTO.ClientDTO;
import com.example.projet.Services.Models.Client;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public ClientDTO toClientDTO(Client client){
        return modelMapper.map(client,ClientDTO.class);
    }

    public Client toClient(ClientDTO clientDTO){
        return modelMapper.map(clientDTO,Client.class);
    }
}
