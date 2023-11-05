package com.example.projet.Services.Voyages;

import com.example.projet.Exceptions.VoyageNotFoundException;
import com.example.projet.Model.Voyage;
import com.example.projet.Repositories.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class iServiceVoyageImpl implements iServiceVoyage {
    @Autowired
    private VoyageRepository voyageRepository;

    @Override
    public Voyage saveVoyage(Voyage voyage) {
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
        return null;
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
