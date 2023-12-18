package com.example.projet.Services.Models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idv")
public class Voyage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idv;
    @Column(nullable = false)
    private String lieudepart;
    @Column(nullable = false)
    private String lieuarrivee;
    @Column(nullable = false)
    private LocalDateTime heuredepart;
    @Column(nullable = false)
    private LocalDateTime heurearrivee;
    @Column(nullable = false)
    private Double prix;
    @OneToMany(mappedBy = "voyage",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
