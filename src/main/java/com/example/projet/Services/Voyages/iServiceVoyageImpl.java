package com.example.projet.Services.Voyages;

import com.example.projet.DTO.VoyageDTO;
import com.example.projet.Exceptions.*;
import com.example.projet.Mappers.VoyageDTOConverter;
import com.example.projet.Models.Voyage;
import com.example.projet.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class iServiceVoyageImpl implements iServiceVoyage {
    @Autowired
    private VoyageRepository voyageRepository;
    @Autowired
    private VoyageDTOConverter dtoConverter;

    private boolean exists(Voyage voyage) {
        for (Voyage v : voyageRepository.findAll())
            if (v.getLieuarrivee().equals(voyage.getLieuarrivee()) && v.getLieudepart().equals(voyage.getLieudepart())
                    && v.getHeuredepart().equals(voyage.getHeuredepart()) && v.getHeurearrivee().equals(voyage.getHeurearrivee()))
                return true;
        return false;
    }

    @Override
    public VoyageDTO saveVoyage(VoyageDTO voyageDTO) {
        Voyage voyage = dtoConverter.toVoyage(voyageDTO);
        if (exists(voyage))
            throw new DuplicateVoyageException("Trip exists already");
        Voyage savedVoyage = voyageRepository.save(voyage);
        return dtoConverter.toVoyageDTO(savedVoyage);
    }

    @Override
    public List<VoyageDTO> getAllVoyages() {
        return voyageRepository.findAll().stream().map(dtoConverter::toVoyageDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VoyageDTO getVoyageById(Long id) {
        Voyage voyage = voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException(id));
        return dtoConverter.toVoyageDTO(voyage);
    }

    @Override
    public VoyageDTO updateVoyage(Long id, VoyageDTO voyageDTO) {
        Voyage updatedVoyage = dtoConverter.toVoyage(voyageDTO);
        return voyageRepository.findById(id).map(voyage -> {
            if (exists(updatedVoyage))
                throw new DuplicateVoyageException("Trip exists already!");
            voyage.setLieudepart(updatedVoyage.getLieudepart());
            voyage.setLieuarrivee(updatedVoyage.getLieuarrivee());
            voyage.setHeuredepart(updatedVoyage.getHeuredepart());
            voyage.setHeurearrivee(updatedVoyage.getHeurearrivee());
            voyage.setPrix(updatedVoyage.getPrix());
            return dtoConverter.toVoyageDTO(voyageRepository.save(voyage));
        }).orElseThrow(() -> new VoyageNotFoundException(id));
    }

    @Override
    public String deleteVoyage(Long id) {
        Voyage voyage = voyageRepository.findById(id).orElse(null);
        if (voyage == null)
            throw new VoyageNotFoundException(id);
        voyageRepository.deleteById(id);
        return "Trip with ID " + id + " has been deleted successfully!";
    }


//    private boolean exists(Voyage voyage){
//        for(Voyage v : voyageRepository.findAll())
//            if(v.getLieuarrivee().equals(voyage.getLieuarrivee()) && v.getLieudepart().equals(voyage.getLieudepart())
//                    && v.getHeuredepart().equals(voyage.getHeuredepart()) && v.getHeurearrivee().equals(voyage.getHeurearrivee()))
//                return true;
//        return false;
//    }
//
//    @Override
//    public Voyage saveVoyage(Voyage voyage) {
//        if (exists(voyage))
//            throw new DuplicateVoyageException("Trip exists already");
//        return voyageRepository.save(voyage);
//    }
//
//    @Override
//    public List<Voyage> getAllVoyages() {
//        return voyageRepository.findAll();
//    }
//
//    @Override
//    public Voyage getVoyageById(Long id) {
//        return voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException(id));
//    }
//
//    @Override
//    public Voyage updateVoyage(Long id, Voyage voyage) {
//        return voyageRepository.findById(id).map(v -> {
//            if (exists(voyage))
//                throw new DuplicateVoyageException("Trip exists already !");
//            v.setLieudepart(voyage.getLieudepart());
//            v.setLieuarrivee(voyage.getLieuarrivee());
//            v.setHeuredepart(voyage.getHeuredepart());
//            v.setHeurearrivee(voyage.getHeurearrivee());
//            v.setPrix(voyage.getPrix());
//            return voyageRepository.save(v);
//        }).orElseThrow(() -> new VoyageNotFoundException(id));
//    }
//
//    @Override
//    public String deleteVoyage(Long id) {
//        Voyage voyage = voyageRepository.findById(id).orElse(null);
//        if (voyage == null)
//            throw new VoyageNotFoundException(id);
//        voyageRepository.deleteById(id);
//        return "Trip with ID " + id + " has been deleted successfully!";
//    }
}
