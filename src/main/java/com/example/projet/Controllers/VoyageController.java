package com.example.projet.Controllers;

import com.example.projet.DTO.VoyageDTO;
import com.example.projet.Services.Voyages.iServiceVoyage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3004")
public class VoyageController {
    @Autowired
    private iServiceVoyage serviceVoyage;


    @PostMapping("/savevoyage")
    ResponseEntity<VoyageDTO> newVoyage(@RequestBody VoyageDTO voyageDTO) {
        VoyageDTO savedVoyageDTO = serviceVoyage.saveVoyage(voyageDTO);
        return new ResponseEntity<>(savedVoyageDTO, HttpStatus.CREATED);
    }

    @GetMapping("/voyages")
    List<VoyageDTO> allVoyages() {
        return serviceVoyage.getAllVoyages();
    }

    @GetMapping("/voyages/{id}")
    VoyageDTO getVoyageByID(@PathVariable Long id) {
        return serviceVoyage.getVoyageById(id);
    }

    @PutMapping("/voyages/{id}")
    ResponseEntity<VoyageDTO> updateVoyage(@RequestBody VoyageDTO updatedVoyageDTO, @PathVariable Long id) {
        VoyageDTO updatedDTO = serviceVoyage.updateVoyage(id, updatedVoyageDTO);
        return new ResponseEntity<>(updatedDTO, HttpStatus.OK);
    }

    @DeleteMapping("/voyages/{id}")
    public ResponseEntity<String> deleteVoyage(@PathVariable Long id) {
        String result = serviceVoyage.deleteVoyage(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

//    @PostMapping("/savevoyage")
//    Voyage newVoyage(@RequestBody Voyage voyage) {
//        return serviceVoyage.saveVoyage(voyage);
//    }
//
//    @GetMapping("/voyages")
//    List<Voyage> allVoyages() {
//        return serviceVoyage.getAllVoyages();
//    }
//
//    @GetMapping("/voyages/{id}")
//    Voyage getVoyageByID(@PathVariable Long id) {
//        return serviceVoyage.getVoyageById(id);
//    }
//
//    @PutMapping("/voyages/{id}")
//    Voyage updateVoyage(@RequestBody Voyage newUser, @PathVariable Long id) {
//        return serviceVoyage.updateVoyage(id, newUser);
//    }
//
//    @DeleteMapping("/voyages/{id}")
//    public ResponseEntity<String> deleteVoyage(@PathVariable Long id) {
//        String result = serviceVoyage.deleteVoyage(id);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }
}
