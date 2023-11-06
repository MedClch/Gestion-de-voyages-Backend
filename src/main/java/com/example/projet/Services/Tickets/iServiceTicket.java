package com.example.projet.Services.Tickets;

import com.example.projet.Model.Ticket;

import java.util.List;

public interface iServiceTicket {
    Ticket saveTicket(Ticket ticket, Long voyageId, Long clientId);
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    Ticket updateTicket(Long id,Ticket ticket);
    String deleteTicket(Long id);
}
