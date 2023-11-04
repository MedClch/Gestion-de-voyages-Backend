package com.example.projet.Controller;

import com.example.projet.Model.Ticket;
import com.example.projet.Model.Voyage;
import com.example.projet.Services.Tickets.iServiceTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TicketController {
    @Autowired
    private iServiceTicket serviceTicket;
    @PostMapping("/saveticket")
    Ticket newTicket(@RequestBody Ticket ticket) {
        return serviceTicket.saveTicket(ticket);
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
    Ticket updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
        return serviceTicket.updateTicket(id, ticket);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        String result = serviceTicket.deleteTicket(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
