package com.example.projet.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
//    @Column(nullable = false)
//    private LocalDateTime depart;
//    @Column(nullable = false)
//    private LocalDateTime arrivee;
//    @Column(nullable = false)
//    private Double prix;
}
