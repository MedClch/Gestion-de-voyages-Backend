package com.example.projet.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTicket;
    @ManyToOne
    @JoinColumn(name = "voyage_id")
    private Voyage voyage;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
