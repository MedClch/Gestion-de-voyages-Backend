package com.example.projet.Services.Tickets;

import com.example.projet.Exceptions.TicketNotFoundException;
import com.example.projet.Model.Ticket;
import com.example.projet.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class iServiceTicketImpl implements iServiceTicket {
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Override
    public Ticket updateTicket(Long id, Ticket ticket) {
        return null;
    }

    @Override
    public String deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null)
            throw new TicketNotFoundException(id);
        ticketRepository.deleteById(id);
        return "Ticket with ID " + id + " has been deleted successfully!";
    }
}
