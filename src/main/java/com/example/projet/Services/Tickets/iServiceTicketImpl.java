package com.example.projet.Services.Tickets;

import com.example.projet.DTO.TicketDTO;
import com.example.projet.Exceptions.TicketNotFoundException;
import com.example.projet.Mappers.TicketDTOConverter;
import com.example.projet.Models.Client;
import com.example.projet.Models.Ticket;
import com.example.projet.Models.Voyage;
import com.example.projet.Repositories.ClientRepository;
import com.example.projet.Repositories.TicketRepository;
import com.example.projet.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class iServiceTicketImpl implements iServiceTicket {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TicketDTOConverter dtoConverter;

    private boolean removeTicketFromVoyage(Ticket ticket) {
        for (Voyage v : voyageRepository.findAll()) {
            if (v.equals(ticket.getVoyage())) {
                v.getTickets().remove(ticket);
                return true;
            }
        }
        return false;
    }

    private boolean removeTicketFromClient(Ticket ticket) {
        for (Client c : clientRepository.findAll()) {
            if (c.equals(ticket.getClient())) {
                c.getTickets().remove(ticket);
                return true;
            }
        }
        return false;
    }

    private boolean isValidTicketCreation(Voyage voyage, Client client) {
        return voyage != null && client != null && !existsByClientAndVoyage(client, voyage);
    }

    @Override
    public TicketDTO saveTicket(TicketDTO ticketDTO, Long voyageId, Long clientId) {
            Voyage voyage = voyageRepository.findById(voyageId).orElse(null);
            Client client = clientRepository.findById(clientId).orElse(null);
            if (isValidTicketCreation(voyage, client)) {
                Ticket ticket = dtoConverter.toTicket(ticketDTO);
                ticket.setVoyage(voyage);
                ticket.setClient(client);
                Ticket savedTicket = ticketRepository.save(ticket);
                return dtoConverter.toTicketDTO(savedTicket);
            } else {
                return null;
            }
    }

    @Override
    public List<TicketDTO> getAllTicketsDTO() {
        return ticketRepository.findAll().stream()
                .map(dtoConverter::toTicketDTO)
                .toList();
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public TicketDTO getTicketDTOById(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
        return dtoConverter.toTicketDTO(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Transactional
    @Override
    public TicketDTO updateTicket(Long id, TicketDTO ticketDTO) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket existingTicket = optionalTicket.get();
            if (ticketDTO.getVoyageId() != null && removeTicketFromVoyage(existingTicket))
                existingTicket.setVoyage(voyageRepository.findById(ticketDTO.getVoyageId()).orElse(null));
            if (ticketDTO.getClientId() != null && removeTicketFromClient(existingTicket))
                existingTicket.setClient(clientRepository.findById(ticketDTO.getClientId()).orElse(null));
            Ticket updatedTicket = ticketRepository.save(existingTicket);
            return dtoConverter.toTicketDTO(updatedTicket);
        } else
            return null;
    }

//    @Transactional
//    @Override
//    public Ticket updateTicket1(Long id, TicketDTO ticketDTO) {
//        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
//        if (optionalTicket.isPresent()) {
//            Ticket existingTicket1 = optionalTicket.get();
//            if (ticketDTO.getVoyageId() != null && removeTicketFromVoyage(existingTicket1))
//                existingTicket1.setVoyage(voyageRepository.findById(ticketDTO.getVoyageId()).orElse(null));
//            if (ticketDTO.getClientId() != null && removeTicketFromClient(existingTicket1))
//                existingTicket1.setClient(clientRepository.findById(ticketDTO.getClientId()).orElse(null));
//            return ticketRepository.save(existingTicket1);
//        } else
//            return null;
//    }

    private boolean existsByClientAndVoyage(Client client, Voyage voyage) {
        for (Ticket t : ticketRepository.findAll())
            if (t.getVoyage().equals(voyage) && t.getClient().equals(client))
                return true;
        return false;
    }

    @Override
    public String deleteTicket(Long id) {
        Ticket ticket = ticketRepository.findById(id).orElse(null);
        if (ticket == null) {
            throw new TicketNotFoundException(id);
        }
        if (removeTicketFromVoyage(ticket)) {
            ticketRepository.deleteById(id);
        }
        return "Ticket with ID " + id + " has been deleted successfully!";
    }

//    private boolean removeTicketfromVoyage(Ticket ticket){
//        for(Voyage v : voyageRepository.findAll()){
//            if(v.equals(ticket.getVoyage())){
//                v.getTickets().remove(ticket);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean removeTicketfromVClient(Ticket ticket){
//        for(Client c : clientRepository.findAll()){
//            if(c.equals(ticket.getClient())){
//                c.getTickets().remove(ticket);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public Ticket saveTicket(Ticket ticket, Long voyageId, Long clientId) {
//        Voyage voyage = voyageRepository.findById(voyageId).orElse(null);
//        Client client = clientRepository.findById(clientId).orElse(null);
//        if (voyage != null && client != null
//                && !existsByClientAndVoyage(client,voyage)) {
//            ticket.setVoyage(voyage);
//            ticket.setClient(client);
//            return ticketRepository.save(ticket);
//        } else
//            return null;
//    }
//
//    @Override
//    public List<Ticket> getAllTickets() {
//        return ticketRepository.findAll();
//    }
//
//    @Override
//    public Ticket getTicketById(Long id) {
//        return ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
//    }
//
////    @Override
////    public Ticket updateTicket(Long id, Ticket ticket) {
////        Optional<Ticket> existyingTicketOptional = ticketRepository.findById(id);
////        if (existingTicketOptional.isEmpty())
////            throw new TicketNotFoundException(id);
////        Ticket existingTicket = existingTicketOptional.get();
////        existingTicket.setVoyage(ticket.getVoyage());
////        existingTicket.setClient(ticket.getClient());
////        return ticketRepository.save(existingTicket);
////    }
//
//    @Transactional
//    @Override
//    public Ticket updateTicket(Long id, Ticket ticket) {
//        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
//        if (optionalTicket.isPresent()) {
//            Ticket existingTicket = optionalTicket.get();
//            if (ticket.getVoyage() != null && removeTicketfromVoyage(existingTicket)) {
//                System.out.println("New trip");
//                existingTicket.setVoyage(ticket.getVoyage());
////                removeTicketfromVoyage(existingTicket);
//            }
//            if (ticket.getClient() != null && removeTicketfromVClient(existingTicket)) {
//                System.out.println("New client");
//                existingTicket.setClient(ticket.getClient());
////                removeTicketfromVClient(existingTicket);
//            }
//            return ticketRepository.save(existingTicket);
//        } else
//            return null;
////        return ticketRepository.findById(id).map(existingTicket -> {
////            if (existsByClientAndVoyage(ticket.getClient(), ticket.getVoyage()))
////                throw new DuplicateTicketException("Trip exists already!");
////            if(removeTicketfromVoyage(ticket) && removeTicketfromVClient(ticket)){
////                existingTicket.setClient(ticket.getClient());
////                existingTicket.setVoyage(ticket.getVoyage());
////            }
//        // or
////            if(removeTicketfromVoyage(ticket) && removeTicketfromVClient(ticket)){
////                existingTicket.setClient(ticket.getClient());
////                existingTicket.setVoyage(ticket.getVoyage());
////            }
////            return ticketRepository.save(existingTicket);
////                }).orElseThrow(() -> new VoyageNotFoundException(id));
//    }

//    private boolean existsByClientAndVoyage(Client client, Voyage voyage) {
//        for(Ticket t : ticketRepository.findAll())
//            if(t.getVoyage().equals(voyage) && t.getClient().equals(client))
//                return true;
//        return false;
//    }



//    @Override
//    public Ticket updateTicket(Long id, Ticket ticket) {
//        return ticketRepository.findById(id).map(t -> {
//            if (exists(ticket))
//                throw new DuplicateTicketException("Trip exists already !");
//            t.setClient(ticket.getClient());
//            t.setVoyage(ticket.getVoyage());
//            return ticketRepository.save(t);
//        }).orElseThrow(() -> new VoyageNotFoundException(id));
//    }
//
//    private boolean exists(Ticket ticket) {
//        for(Ticket t : ticketRepository.findAll())
//            if(t.equals(ticket))
//                return true;
//        return false;
//    }

//    @Override
//    public String deleteTicket(Long id) {
//        Ticket ticket = ticketRepository.findById(id).orElse(null);
//        if (ticket == null)
//            throw new TicketNotFoundException(id);
//        if(removeTicketfromVoyage(ticket))
//            ticketRepository.deleteById(id);
//        return "Ticket with ID " + id + " has been deleted successfully!";
//    }

}
