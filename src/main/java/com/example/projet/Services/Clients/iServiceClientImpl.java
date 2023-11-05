package com.example.projet.Services.Clients;

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
public class iServiceClientImpl implements iServiceClient {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client saveClient(Client client) {
        if (clientRepository.findByUsername(client.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (clientRepository.findByEmail(client.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        client.setPassword(hashPassword(client.getPassword()));
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Client updateClient(Long id, Client newUser) {
        return clientRepository.findById(id).map(client -> {
            if (!client.getUsername().equals(newUser.getUsername()) &&
                    clientRepository.findByUsername(newUser.getUsername()) != null)
                throw new DuplicateUsernameException("Username is already taken");
            if (!client.getEmail().equals(newUser.getEmail()) &&
                    clientRepository.findByEmail(newUser.getEmail()) != null)
                throw new DuplicateEmailException("Email is already registered");
            client.setFullname(newUser.getFullname());
            client.setUsername(newUser.getUsername());
            client.setEmail(newUser.getEmail());
//            user.setPassword(newUser.getPassword());
            client.setPassword(hashPassword(newUser.getPassword()));
            return clientRepository.save(client);
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public String deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null)
            throw new UserNotFoundException(id);
        clientRepository.deleteById(id);
        return "User with ID " + id + " has been deleted successfully!";
    }

    @Override
    public Client registerClient(Client client) {
        if (clientRepository.findByUsername(client.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (clientRepository.findByEmail(client.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        String hashedPassword = hashPassword(client.getPassword());
        client.setPassword(hashedPassword);
        return clientRepository.save(client);
    }

    @Override
    public Client loginClient(String username, String password) {
        Client client = clientRepository.findByUsername(username);
        if (client == null)
            return null;
        if (verifyPassword(password, client.getPassword()))
            return client;
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
