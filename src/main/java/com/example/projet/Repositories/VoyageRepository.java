package com.example.projet.Repositories;

import com.example.projet.Services.Models.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage,Long> {
    Voyage findByLieudepart(String lieudepart);
    Voyage findByLieuarrivee(String lieudepart);
    Voyage findByHeurearrivee(LocalDateTime heurearrivee);
    Voyage findByHeuredepart(LocalDateTime heuredepart);
}
