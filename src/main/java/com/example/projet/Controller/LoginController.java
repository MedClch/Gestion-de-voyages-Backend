package com.example.projet.Controller;

import com.example.projet.Model.Utilisateur;
import com.example.projet.Services.iServiceUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:3000")
public class LoginController {
    @Autowired
    private iServiceUtilisateur utilisateurService;

    @PostMapping("/login")
    public ResponseEntity<Utilisateur> loginUser(@RequestParam String username, @RequestParam String password) {
        Utilisateur loggedInUser = utilisateurService.loginUser(username, password);
        if (loggedInUser != null)
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
