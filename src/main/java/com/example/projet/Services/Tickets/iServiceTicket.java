package com.example.projet.Services.Tickets;

import com.example.projet.Model.Ticket;

import java.util.List;

public interface iServiceTicket {
    Ticket saveTicket(Ticket ticket);
    List<Ticket> getAllTickets();
    Ticket getTicketById(Long id);
    Ticket updateTicket(Long id,Ticket ticket);
    String deleteTicket(Long id);
}
