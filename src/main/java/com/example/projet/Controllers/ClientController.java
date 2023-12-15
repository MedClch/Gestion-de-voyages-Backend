package com.example.projet.Controllers;

import com.example.projet.Models.Client;
import com.example.projet.Services.Clients.iServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3004")
public class ClientController {

    @Autowired
    private iServiceClient serviceUtilisateur;

    @PostMapping("/saveclient")
    Client newUser(@RequestBody Client client) {
        return serviceUtilisateur.saveClient(client);
    }

    @GetMapping("/clients")
    List<Client> allUsers() {
        return serviceUtilisateur.getAllClients();
    }

    @GetMapping("/client/{id}")
    Client getUserByID(@PathVariable Long id) {
        return serviceUtilisateur.getClientById(id);
    }

    @PutMapping("/client/{id}")
    Client updateUser(@RequestBody Client newUser, @PathVariable Long id) {
        return serviceUtilisateur.updateClient(id, newUser);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String result = serviceUtilisateur.deleteClient(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
