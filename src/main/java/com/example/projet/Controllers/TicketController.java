package com.example.projet.Controllers;

import com.example.projet.DTO.ClientDTO;
import com.example.projet.DTO.TicketDTO;
import com.example.projet.DTO.VoyageDTO;
import com.example.projet.Services.Models.Client;
import com.example.projet.Services.Models.Ticket;
import com.example.projet.Services.Clients.iServiceClient;
import com.example.projet.Services.Models.Voyage;
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
    public ResponseEntity<TicketDTO> newTicket(@RequestBody TicketDTO ticketDTO,
                                               @RequestParam Long voyageId, @RequestParam Long clientId) {
        if (voyageId == null || clientId == null)
            return ResponseEntity.badRequest().build();
        if (serviceClient.getClientById(clientId) == null || serviceVoyage.getVoyageById(voyageId) == null)
            return ResponseEntity.badRequest().build();
        TicketDTO savedTicketDTO = serviceTicket.saveTicket(ticketDTO, voyageId, clientId);
        if (savedTicketDTO != null)
            return new ResponseEntity<>(savedTicketDTO, HttpStatus.CREATED);
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/savetickett")
    public ResponseEntity<TicketDTO> newTicket1(@RequestBody TicketDTO ticketDTO,
                                               @RequestParam Long voyageId, @RequestParam Long clientId) {
        if (voyageId == null || clientId == null)
            return ResponseEntity.badRequest().build();

        // Set the voyageId and clientId in the ticketDTO
        ticketDTO.setVoyageId(voyageId);
        ticketDTO.setClientId(clientId);

        if (serviceClient.getClientById(clientId) == null || serviceVoyage.getVoyageById(voyageId) == null)
            return ResponseEntity.badRequest().build();

        TicketDTO savedTicketDTO = serviceTicket.saveTicket(ticketDTO, voyageId, clientId);
        if (savedTicketDTO != null)
            return new ResponseEntity<>(savedTicketDTO, HttpStatus.CREATED);
        else
            return ResponseEntity.badRequest().build();
    }


    @PostMapping("/addticket")
    public ResponseEntity<TicketDTO> newTicket2(@RequestBody TicketDTO ticketDTO,
                                               @RequestParam Long voyageId, @RequestParam Long clientId) {
        if (voyageId == null || clientId == null)
            return ResponseEntity.badRequest().build();
        ClientDTO existingClient = serviceClient.getClientById(clientId);
        VoyageDTO existingVoyage = serviceVoyage.getVoyageById(voyageId);
        if (existingClient == null || existingVoyage == null)
            return ResponseEntity.badRequest().build();
        TicketDTO savedTicketDTO = serviceTicket.saveTicket(ticketDTO, voyageId, clientId);
        if (savedTicketDTO != null)
            return new ResponseEntity<>(savedTicketDTO, HttpStatus.CREATED);
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/tickets")
    List<TicketDTO> allTickets() {
        return serviceTicket.getAllTicketsDTO();
    }

    @GetMapping("/ticketss")
    List<Ticket> allTickets1() {
        return serviceTicket.getAllTickets();
    }
    @GetMapping("/tickets/{id}")
    ResponseEntity<TicketDTO> getTicketByID(@PathVariable Long id) {
        TicketDTO ticketDTO = serviceTicket.getTicketDTOById(id);
        return ticketDTO != null ? ResponseEntity.ok(ticketDTO)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/ticketss/{id}")
    ResponseEntity<Ticket> getTicketByID1(@PathVariable Long id) {
    Ticket ticket = serviceTicket.getTicketById(id);
        return ticket != null ? ResponseEntity.ok(ticket)
            : ResponseEntity.notFound().build();
    }

    //! this method returns a ticketDTO
    @PutMapping("/tickets/{id}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable Long id,
                                                  @RequestBody TicketDTO updatedTicketDTO) {
        TicketDTO updatedDTO = serviceTicket.updateTicket(id, updatedTicketDTO);
        return updatedDTO != null ? ResponseEntity.ok(updatedDTO)
                : ResponseEntity.notFound().build();
    }

//    //! this method returns a ticket object
//    @PutMapping("/ticketss/{id}")
//    public ResponseEntity<Ticket> updateTicket1(@PathVariable Long id,
//                                                  @RequestBody TicketDTO updatedTicketDTO) {
//        Ticket updated = serviceTicket.updateTicket1(id, updatedTicketDTO);
//        return updated != null ? ResponseEntity.ok(updated)
//                : ResponseEntity.notFound().build();
//    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        String result = serviceTicket.deleteTicket(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/saveticket")
//    public Ticket newTicket(@RequestBody Ticket ticket, @RequestParam Long voyageId, @RequestParam Long clientId) {
//        if (voyageId == null || clientId == null)
//            return null;
//        if(serviceClient.getClientById(clientId)==null || serviceVoyage.getVoyageById(voyageId)==null)
//            return null;
////        ticket.setVoyage(serviceVoyage.getVoyageById(voyageId));
////        ticket.setClient(serviceClient.getClientById(clientId));
//        return serviceTicket.saveTicket(ticket, voyageId, clientId);
//    }
//
//
//    @GetMapping("/tickets")
//    List<Ticket> allTickets() {
//        return serviceTicket.getAllTickets();
//    }
//
//    @GetMapping("/tickets/{id}")
//    Ticket getTicketByID(@PathVariable Long id) {
//        return serviceTicket.getTicketById(id);
//    }
//
//    @PutMapping("/tickets/{id}")
//    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket newTicket) {
//        Ticket updatedTicket = serviceTicket.updateTicket(id, newTicket);
//        if (updatedTicket != null)
//            return ResponseEntity.ok(updatedTicket);
//        else
//            return ResponseEntity.notFound().build();
//    }


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
}
