package com.example.projet.Services;

import com.example.projet.Exceptions.DuplicateEmailException;
import com.example.projet.Exceptions.DuplicateUsernameException;
import com.example.projet.Exceptions.UserNotFoundException;
import com.example.projet.Model.Utilisateur;
import com.example.projet.Repositories.UtilisateurRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceUtilisateurImpl implements iServiceUtilisateur{
    @Autowired
    private UtilisateurRepository repository;

    @Override
    public Utilisateur saveUser(Utilisateur utilisateur) {
        if (repository.findByUsername(utilisateur.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (repository.findByEmail(utilisateur.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        return repository.save(utilisateur);
    }

    @Override
    public List<Utilisateur> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public Utilisateur getUserById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Utilisateur updateUser(Long id, Utilisateur newUser) {
        return repository.findById(id).map(user -> {
            if (!user.getUsername().equals(newUser.getUsername()) &&
                    repository.findByUsername(newUser.getUsername()) != null)
                throw new DuplicateUsernameException("Username is already taken");
            if (!user.getEmail().equals(newUser.getEmail()) &&
                    repository.findByEmail(newUser.getEmail()) != null)
                throw new DuplicateEmailException("Email is already registered");
            user.setFullname(newUser.getFullname());
            user.setUsername(newUser.getUsername());
            user.setEmail(newUser.getEmail());
            user.setPassword(user.getPassword());
            return repository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Utilisateur registerUser(Utilisateur utilisateur) {
        if (repository.findByUsername(utilisateur.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (repository.findByEmail(utilisateur.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        String hashedPassword = hashPassword(utilisateur.getPassword());
        utilisateur.setPassword(hashedPassword);
        return repository.save(utilisateur);
    }

    @Override
    public Utilisateur loginUser(String username, String password) {
        Utilisateur user = repository.findByUsername(username);
        if (user == null)
            return null;
        if (verifyPassword(password, user.getPassword()))
            return user;
        else
            return null;
    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
//    private boolean findUserByData(String text){
//        boolean exists = false;
//        for(Utilisateur utilisateur : getAllUsers()){
//            if (utilisateur.getEmail().equals(text) || utilisateur.getUsername().equals(text)) {
//                exists = true;
//                break;
//            }
//        }
//        return exists;
//    }
//
//    private Utilisateur update(Utilisateur utilisateur,Long id){
//        for(Utilisateur user : getAllUsers()){
//            if (user.getId().equals(id)) {
//                user.setUsername(utilisateur.getUsername());
//                user.setFullname(utilisateur.getFullname());
//                user.setEmail(utilisateur.getEmail());
//                user.setPassword(utilisateur.getPassword());
//                return user;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<Utilisateur> getAllUsers() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Utilisateur newUser(Utilisateur utilisateur) {
//        if(!findUserByData(utilisateur.getEmail()) && !findUserByData(utilisateur.getUsername()))
//            return repository.save(utilisateur);
//        else
//            return null;
//    }
//
//    @Override
//    public Optional<Utilisateur> getUserById(Long id) {
//        return repository.findById(id);
//    }
//
//    @Override
//    public Utilisateur updateUser(Utilisateur utilisateur, Long id) {
//        if(!findUserByData(utilisateur.getEmail()) && !findUserByData(utilisateur.getUsername())
//            && getUserById(id).isPresent()){
//            return update(utilisateur,id);
//        }
//        return null;
//    }
