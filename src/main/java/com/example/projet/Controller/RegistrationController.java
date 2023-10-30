package com.example.projet.Controller;

import com.example.projet.Model.Utilisateur;
import com.example.projet.Services.iServiceUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class RegistrationController {
    @Autowired
    private iServiceUtilisateur utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<Utilisateur> registerUser(@RequestBody Utilisateur utilisateur) {
        Utilisateur registeredUser = utilisateurService.registerUser(utilisateur);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
