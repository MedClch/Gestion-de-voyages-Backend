package com.example.projet.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoyageDTO {
    private Long idv;
    private String lieudepart;
    private String lieuarrivee;
    private LocalDateTime heuredepart;
    private LocalDateTime heurearrivee;
    private Double prix;
}

