package com.example.projet.Services.Tickets;

import com.example.projet.Exceptions.TicketNotFoundException;
import com.example.projet.Model.Client;
import com.example.projet.Model.Ticket;
import com.example.projet.Model.Voyage;
import com.example.projet.Repositories.ClientRepository;
import com.example.projet.Repositories.TicketRepository;
import com.example.projet.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class iServiceTicketImpl implements iServiceTicket {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private ClientRepository clientRepository;

    private boolean removeTicketfromVoyage(Ticket ticket){
        for(Voyage v : voyageRepository.findAll()){
            if(v.equals(ticket.getVoyage())){
                v.getTickets().remove(ticket);
                return true;
            }
        }
        return false;
    }

    @Override
    public Ticket saveTicket(Ticket ticket, Long voyageId, Long clientId) {
        Voyage voyage = voyageRepository.findById(voyageId).orElse(null);
        Client client = clientRepository.findById(clientId).orElse(null);

        if (voyage != null && client != null) {
            ticket.setVoyage(voyage);
            ticket.setClient(client);
            return ticketRepository.save(ticket);
        } else
            return null;
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
        if(removeTicketfromVoyage(ticket))
            ticketRepository.deleteById(id);
        return "Ticket with ID " + id + " has been deleted successfully!";
    }
}
