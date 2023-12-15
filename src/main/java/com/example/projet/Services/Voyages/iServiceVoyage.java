package com.example.projet.Services.Voyages;

import com.example.projet.Models.Voyage;

import java.util.List;

public interface iServiceVoyage {
    Voyage saveVoyage(Voyage voyage);
    List<Voyage> getAllVoyages();
    Voyage getVoyageById(Long id);
    Voyage updateVoyage(Long id,Voyage voyage);
    String deleteVoyage(Long id);
}
