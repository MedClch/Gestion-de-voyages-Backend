package com.example.projet.Services.Tickets;

import com.example.projet.DTO.TicketDTO;
import com.example.projet.Services.Models.Ticket;

import java.util.List;

public interface iServiceTicket {
    TicketDTO saveTicket(TicketDTO ticketDTO, Long voyageId, Long clientId);
    List<TicketDTO> getAllTicketsDTO();
    List<Ticket> getAllTickets();
    TicketDTO getTicketDTOById(Long id);
    Ticket getTicketById(Long id);
    TicketDTO updateTicket(Long id,TicketDTO ticketDTO);
//    Ticket updateTicket1(Long id, TicketDTO ticketDTO);
    String deleteTicket(Long id);
}
