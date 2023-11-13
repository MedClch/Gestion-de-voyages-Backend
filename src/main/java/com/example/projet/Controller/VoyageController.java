package com.example.projet.Controller;

import com.example.projet.Model.Voyage;
import com.example.projet.Services.Voyages.iServiceVoyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3004")
public class VoyageController {
    @Autowired
    private iServiceVoyage serviceVoyage;
    @PostMapping("/savevoyage")
    Voyage newVoyage(@RequestBody Voyage voyage) {
        return serviceVoyage.saveVoyage(voyage);
    }

    @GetMapping("/voyages")
    List<Voyage> allVoyages() {
        return serviceVoyage.getAllVoyages();
    }

    @GetMapping("/voyage/{id}")
    Voyage getVoyageByID(@PathVariable Long id) {
        return serviceVoyage.getVoyageById(id);
    }

    @PutMapping("/voyage/{id}")
    Voyage updateVoyage(@RequestBody Voyage newUser, @PathVariable Long id) {
        return serviceVoyage.updateVoyage(id, newUser);
    }

    @DeleteMapping("/voyage/{id}")
    public ResponseEntity<String> deleteVoyage(@PathVariable Long id) {
        String result = serviceVoyage.deleteVoyage(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
