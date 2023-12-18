package com.example.projet.Mappers;

import com.example.projet.DTO.TicketDTO;
import com.example.projet.Services.Models.Ticket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public TicketDTO toTicketDTO(Ticket ticket){
        return modelMapper.map(ticket,TicketDTO.class);
    }

    public Ticket toTicket(TicketDTO ticketDTO){
        return modelMapper.map(ticketDTO,Ticket.class);
    }
}
