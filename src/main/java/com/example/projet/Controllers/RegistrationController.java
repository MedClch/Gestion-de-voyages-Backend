package com.example.projet.Controllers;

import com.example.projet.DTO.ClientDTO;
import com.example.projet.Services.Clients.iServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3004")
public class RegistrationController {
    @Autowired
    private iServiceClient utilisateurService;

    @PostMapping("/register")
    public ResponseEntity<ClientDTO> registerUser(@RequestBody ClientDTO clientDTO) {
        ClientDTO registeredUserDTO = utilisateurService.registerClient(clientDTO);
        return new ResponseEntity<>(registeredUserDTO, HttpStatus.CREATED);
    }

//    @PostMapping("/register")
//    public ResponseEntity<Client> registerUser(@RequestBody Client client) {
//        Client registeredUser = utilisateurService.registerClient(client);
//        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
//    }
}
