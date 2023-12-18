package com.example.projet.Services.Voyages;

import com.example.projet.DTO.VoyageDTO;

import java.util.List;

public interface iServiceVoyage {
    VoyageDTO saveVoyage(VoyageDTO voyageDTO);
    List<VoyageDTO> getAllVoyages();
    VoyageDTO getVoyageById(Long id);
    VoyageDTO updateVoyage(Long id,VoyageDTO voyageDTO);
    String deleteVoyage(Long id);
}
