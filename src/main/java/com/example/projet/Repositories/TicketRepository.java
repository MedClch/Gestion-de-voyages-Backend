package com.example.projet.Repositories;

import com.example.projet.Model.Client;
import com.example.projet.Model.Ticket;
import com.example.projet.Model.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    boolean existsByClientAndVoyage(Client client, Voyage voyage);
}
