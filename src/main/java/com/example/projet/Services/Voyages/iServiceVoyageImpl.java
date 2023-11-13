package com.example.projet.Services.Voyages;

import com.example.projet.Exceptions.*;
import com.example.projet.Model.Voyage;
import com.example.projet.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class iServiceVoyageImpl implements iServiceVoyage {
    @Autowired
    private VoyageRepository voyageRepository;

    private boolean exists(Voyage voyage){
        for(Voyage v : voyageRepository.findAll())
            if(v.getLieuarrivee().equals(voyage.getLieuarrivee()) && v.getLieudepart().equals(voyage.getLieudepart())
                    && v.getHeuredepart().equals(voyage.getHeuredepart()) && v.getHeurearrivee().equals(voyage.getHeurearrivee()))
                return true;
        return false;
    }

    @Override
    public Voyage saveVoyage(Voyage voyage) {
        if (exists(voyage))
            throw new DuplicateVoyageException("Trip exists already");
        return voyageRepository.save(voyage);
    }

    @Override
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    @Override
    public Voyage getVoyageById(Long id) {
        return voyageRepository.findById(id).orElseThrow(() -> new VoyageNotFoundException(id));
    }

    @Override
    public Voyage updateVoyage(Long id, Voyage voyage) {
        return voyageRepository.findById(id).map(v -> {
            if (exists(voyage))
                throw new DuplicateVoyageException("Trip exists already !");
            v.setLieudepart(voyage.getLieudepart());
            v.setLieuarrivee(voyage.getLieuarrivee());
            v.setHeuredepart(voyage.getHeuredepart());
            v.setHeurearrivee(voyage.getHeurearrivee());
            v.setPrix(voyage.getPrix());
            return voyageRepository.save(v);
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
}
