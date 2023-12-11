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
@CrossOrigin(origins = "http://localhost:3004")
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

    @GetMapping("/tickets/{id}")
    Ticket getTicketByID(@PathVariable Long id) {
        return serviceTicket.getTicketById(id);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket newTicket) {
        Ticket updatedTicket = serviceTicket.updateTicket(id, newTicket);
        if (updatedTicket != null)
            return ResponseEntity.ok(updatedTicket);
        else
            return ResponseEntity.notFound().build();
    }
//    public Ticket updateTicket(@RequestBody Ticket newTicket, @PathVariable Long id) {
//        return serviceTicket.updateTicket(id, newTicket);
//    }


//    @PutMapping("/tickets/{id}")
//    public ResponseEntity<Ticket> majTicket(@RequestBody Ticket updatedTicket, @PathVariable Long id,
//                                            @RequestParam Long voyageId,
//                                            @RequestParam Long clientId) {
//        try {
//            Ticket existingTicket = serviceTicket.getTicketById(id);
//            if (existingTicket == null)
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            if (voyageId == null || clientId == null)
//                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//            Client client = serviceClient.getClientById(clientId);
//            Voyage voyage = serviceVoyage.getVoyageById(voyageId);
//            if (client == null || voyage == null)
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            existingTicket.setVoyage(voyage);
//            existingTicket.setClient(client);
//            Ticket savedTicket = serviceTicket.saveTicket(existingTicket, voyageId, clientId);
//            return new ResponseEntity<>(savedTicket, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


// dev1 push test

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        String result = serviceTicket.deleteTicket(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
