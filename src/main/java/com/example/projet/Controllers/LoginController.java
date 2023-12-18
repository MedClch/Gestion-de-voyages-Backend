package com.example.projet.Controllers;

import com.example.projet.DTO.ClientDTO;
import com.example.projet.Services.Clients.iServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3004")
public class LoginController {
    @Autowired
    private iServiceClient utilisateurService;

    @GetMapping("/login")
    public ResponseEntity<ClientDTO> loginUser(@RequestParam String username, @RequestParam String password) {
        ClientDTO loggedInUserDTO = utilisateurService.loginClient(username, password);
        if (loggedInUserDTO != null)
            return new ResponseEntity<>(loggedInUserDTO, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

//    @GetMapping("/login")
//    public ResponseEntity<Client> loginUser(@RequestParam String username, @RequestParam String password) {
//        Client loggedInUser = utilisateurService.loginClient(username, password);
//        if (loggedInUser != null)
//            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//    }
}
