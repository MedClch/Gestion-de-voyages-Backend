package com.example.projet.Models;

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
    private Long idt;
    @ManyToOne
    @JoinColumn(name = "voyage_id",nullable = false)
    private Voyage voyage;
    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
}
