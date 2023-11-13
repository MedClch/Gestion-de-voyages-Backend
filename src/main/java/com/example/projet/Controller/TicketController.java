package com.example.projet.Controller;

import com.example.projet.Exceptions.TicketNotFoundException;
import com.example.projet.Model.Client;
import com.example.projet.Model.Ticket;
import com.example.projet.Model.Voyage;
import com.example.projet.Services.Clients.iServiceClient;
import com.example.projet.Services.Tickets.iServiceTicket;
import com.example.projet.Services.Voyages.iServiceVoyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3004")
public class TicketController {
    @Autowired
    private iServiceTicket serviceTicket;
    @Autowired
    private iServiceVoyage serviceVoyage;
    @Autowired
    private iServiceClient serviceClient;

    @PostMapping("/saveticket")
    public Ticket newTicket(@RequestBody Ticket ticket, @RequestParam Long voyageId, @RequestParam Long clientId) {
        if (voyageId == null || clientId == null)
            return null;
        if(serviceClient.getClientById(clientId)==null || serviceVoyage.getVoyageById(voyageId)==null)
            return null;
        ticket.setVoyage(serviceVoyage.getVoyageById(voyageId));
        ticket.setClient(serviceClient.getClientById(clientId));
        return serviceTicket.saveTicket(ticket, voyageId, clientId);
    }


    @GetMapping("/tickets")
    List<Ticket> allTickets() {
        return serviceTicket.getAllTickets();
    }

    @GetMapping("/ticket/{id}")
    Ticket getTicketByID(@PathVariable Long id) {
        return serviceTicket.getTicketById(id);
    }

    @PutMapping("/ticket/{id}")
    public Ticket updateTicket(@RequestBody Ticket newTicket, @PathVariable Long id) {
        return serviceTicket.updateTicket(id, newTicket);
    }


    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        String result = serviceTicket.deleteTicket(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
