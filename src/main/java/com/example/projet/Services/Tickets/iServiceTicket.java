package com.example.projet.Services.Tickets;

import com.example.projet.DTO.TicketDTO;
import com.example.projet.Models.Ticket;

import java.util.List;

public interface iServiceTicket {
    TicketDTO saveTicket(TicketDTO ticketDTO, Long voyageId, Long clientId);
    List<TicketDTO> getAllTickets();
    TicketDTO getTicketById(Long id);
    TicketDTO updateTicket(Long id,TicketDTO ticketDTO);
    String deleteTicket(Long id);
}
