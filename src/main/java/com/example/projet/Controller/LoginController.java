package com.example.projet.Controller;

import com.example.projet.Model.Client;
import com.example.projet.Services.Clients.iServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/login")
@CrossOrigin("http://localhost:3003")
public class LoginController {
    @Autowired
    private iServiceClient utilisateurService;

    @GetMapping("/login")
    public ResponseEntity<Client> loginUser(@RequestParam String username, @RequestParam String password) {
        Client loggedInUser = utilisateurService.loginClient(username, password);
        if (loggedInUser != null)
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
