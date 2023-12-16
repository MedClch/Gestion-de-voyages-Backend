package com.example.projet.Controllers;

import com.example.projet.DTO.ClientDTO;
import com.example.projet.Services.Clients.iServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3004")
public class AuthController {
    @Autowired
    private iServiceClient utilisateurService;

    @GetMapping("/loginn")
    public ResponseEntity<ClientDTO> loginUser(@RequestParam String username, @RequestParam String password) {
        ClientDTO loggedInUserDTO = utilisateurService.loginClient(username, password);
        if (loggedInUserDTO != null)
            return new ResponseEntity<>(loggedInUserDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/registerr")
    public ResponseEntity<ClientDTO> registerUser(@RequestBody ClientDTO clientDTO) {
        ClientDTO registeredUserDTO = utilisateurService.registerClient(clientDTO);
        return new ResponseEntity<>(registeredUserDTO, HttpStatus.CREATED);
    }
}
