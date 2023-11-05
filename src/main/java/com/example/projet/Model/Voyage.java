package com.example.projet.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoyage;
    @Column(nullable = false)
    private String lieudepart;
    @Column(nullable = false)
    private String lieuarrivee;
    @OneToMany(mappedBy = "voyage")
    private List<Ticket> tickets;
}
