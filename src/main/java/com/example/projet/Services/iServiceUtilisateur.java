package com.example.projet.Services;

import com.example.projet.Model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface iServiceUtilisateur {
    Utilisateur saveUser(Utilisateur utilisateur);
    List<Utilisateur> getAllUsers();
    Utilisateur getUserById(Long id);
    Utilisateur updateUser(Long id, Utilisateur newUser);
    String deleteUser(Long id);
    Utilisateur registerUser(Utilisateur utilisateur);
    Utilisateur loginUser(String username, String password);

//    List<Utilisateur> getAllUsers();
//    Utilisateur newUser(Utilisateur utilisateur);
//    Optional<Utilisateur> getUserById(Long id);
//    Utilisateur updateUser(Utilisateur utilisateur,Long id);

}
