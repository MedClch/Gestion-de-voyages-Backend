package com.example.projet.Mappers;

import com.example.projet.DTO.VoyageDTO;
import com.example.projet.Services.Models.Voyage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VoyageDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public VoyageDTO toVoyageDTO(Voyage voyage){
        return modelMapper.map(voyage,VoyageDTO.class);
    }

    public Voyage toVoyage(VoyageDTO voyageDTO){
        return modelMapper.map(voyageDTO,Voyage.class);
    }
}
