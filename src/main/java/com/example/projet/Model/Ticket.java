package com.example.projet.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private Long idt;
    @ManyToOne
    @JoinColumn(name = "voyage_id")
//    @JsonManagedReference
    private Voyage voyage;
    @ManyToOne
    @JoinColumn(name = "client_id")
//    @JsonManagedReference
    private Client client;
}
