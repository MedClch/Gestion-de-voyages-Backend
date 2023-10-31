package com.example.projet.Controller;

import com.example.projet.Exceptions.UserNotFoundException;
import com.example.projet.Model.Utilisateur;
import com.example.projet.Services.iServiceUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UtilisateurController {

    @Autowired
    private iServiceUtilisateur serviceUtilisateur;

    @PostMapping("/saveuser")
    Utilisateur newUser(@RequestBody Utilisateur utilisateur) {
        return serviceUtilisateur.saveUser(utilisateur);
    }

    @GetMapping("/users")
    List<Utilisateur> allUsers() {
        return serviceUtilisateur.getAllUsers();
    }

    @GetMapping("/user/{id}")
    Utilisateur getUserByID(@PathVariable Long id) {
        return serviceUtilisateur.getUserById(id);
    }

    @PutMapping("/user/{id}")
    Utilisateur updateUser(@RequestBody Utilisateur newUser, @PathVariable Long id) {
        return serviceUtilisateur.updateUser(id, newUser);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = serviceUtilisateur.deleteUser(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
