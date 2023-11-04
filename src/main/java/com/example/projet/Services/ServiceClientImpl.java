package com.example.projet.Services;

import com.example.projet.Exceptions.DuplicateEmailException;
import com.example.projet.Exceptions.DuplicateUsernameException;
import com.example.projet.Exceptions.UserNotFoundException;
import com.example.projet.Model.Client;
import com.example.projet.Repositories.ClientRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceClientImpl implements iServiceClient {
    @Autowired
    private ClientRepository repository;

    @Override
    public Client saveClient(Client client) {
        if (repository.findByUsername(client.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (repository.findByEmail(client.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        client.setPassword(hashPassword(client.getPassword()));
        return repository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Client updateClient(Long id, Client newUser) {
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
//            user.setPassword(newUser.getPassword());
            user.setPassword(hashPassword(newUser.getPassword()));
            return repository.save(user);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public String deleteClient(Long id) {
        Client user = repository.findById(id).orElse(null);
        if (user == null)
            throw new UserNotFoundException(id);
        repository.deleteById(id);
        return "User with ID " + id + " has been deleted successfully!";
    }

    @Override
    public Client registerClient(Client client) {
        if (repository.findByUsername(client.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (repository.findByEmail(client.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        String hashedPassword = hashPassword(client.getPassword());
        client.setPassword(hashedPassword);
        return repository.save(client);
    }

    @Override
    public Client loginClient(String username, String password) {
        Client user = repository.findByUsername(username);
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
//        for(Client utilisateur : getAllUsers()){
//            if (utilisateur.getEmail().equals(text) || utilisateur.getUsername().equals(text)) {
//                exists = true;
//                break;
//            }
//        }
//        return exists;
//    }
//
//    private Client update(Client utilisateur,Long id){
//        for(Client user : getAllUsers()){
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
//    public List<Client> getAllUsers() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Client newUser(Client utilisateur) {
//        if(!findUserByData(utilisateur.getEmail()) && !findUserByData(utilisateur.getUsername()))
//            return repository.save(utilisateur);
//        else
//            return null;
//    }
//
//    @Override
//    public Optional<Client> getUserById(Long id) {
//        return repository.findById(id);
//    }
//
//    @Override
//    public Client updateUser(Client utilisateur, Long id) {
//        if(!findUserByData(utilisateur.getEmail()) && !findUserByData(utilisateur.getUsername())
//            && getUserById(id).isPresent()){
//            return update(utilisateur,id);
//        }
//        return null;
//    }
