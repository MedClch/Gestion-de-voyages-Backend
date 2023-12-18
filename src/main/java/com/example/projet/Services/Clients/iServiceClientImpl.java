package com.example.projet.Services.Clients;

import com.example.projet.DTO.ClientDTO;
import com.example.projet.Services.Exceptions.DuplicateEmailException;
import com.example.projet.Services.Exceptions.DuplicateUsernameException;
import com.example.projet.Services.Exceptions.UserNotFoundException;
import com.example.projet.Mappers.ClientDTOConverter;
import com.example.projet.Services.Models.Client;
import com.example.projet.Repositories.ClientRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class iServiceClientImpl implements iServiceClient {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientDTOConverter clientDTOConverter;

    @Override
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientDTOConverter.toClient(clientDTO);
        if (clientRepository.findByUsername(client.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (clientRepository.findByEmail(client.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        client.setPassword(hashPassword(client.getPassword()));
        Client savedClient = clientRepository.save(client);
        return clientDTOConverter.toClientDTO(savedClient);
//        if (clientRepository.findByUsername(client.getUsername()) != null)
//            throw new DuplicateUsernameException("Username is already taken");
//        if (clientRepository.findByEmail(client.getEmail()) != null)
//            throw new DuplicateEmailException("Email is already registered");
//        client.setPassword(hashPassword(client.getPassword()));
//        return clientRepository.save(client);
    }

//    @Override
//    public List<Client> getAllClients() {
//        return clientRepository.findAll();
//    }

    @Override
    public List<ClientDTO> getClients() {
        return clientRepository.findAll().stream()
                .map(clientDTOConverter::toClientDTO).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return clientDTOConverter.toClientDTO(client);
//        return clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }


    @Override
    public ClientDTO updateClient(Long id, ClientDTO newUserDTO) {
        Client newUser = clientDTOConverter.toClient(newUserDTO);
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
            client.setPassword(hashPassword(newUser.getPassword()));
            return clientDTOConverter.toClientDTO(clientRepository.save(client));
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
    public ClientDTO registerClient(ClientDTO clientDTO) {
        Client client = clientDTOConverter.toClient(clientDTO);
        if (clientRepository.findByUsername(client.getUsername()) != null)
            throw new DuplicateUsernameException("Username is already taken");
        if (clientRepository.findByEmail(client.getEmail()) != null)
            throw new DuplicateEmailException("Email is already registered");
        String hashedPassword = hashPassword(client.getPassword());
        client.setPassword(hashedPassword);
        return clientDTOConverter.toClientDTO(clientRepository.save(client));
    }

    @Override
    public ClientDTO loginClient(String username, String password) {
        Client client = clientRepository.findByUsername(username);
        if (client == null)
            return null;
        if (verifyPassword(password, client.getPassword()))
            return clientDTOConverter.toClientDTO(client);
        else
            return null;
    }

//    @Override
//    public Client updateClient(Long id, Client newUser) {
//        return clientRepository.findById(id).map(client -> {
//            if (!client.getUsername().equals(newUser.getUsername()) &&
//                    clientRepository.findByUsername(newUser.getUsername()) != null)
//                throw new DuplicateUsernameException("Username is already taken");
//            if (!client.getEmail().equals(newUser.getEmail()) &&
//                    clientRepository.findByEmail(newUser.getEmail()) != null)
//                throw new DuplicateEmailException("Email is already registered");
//            client.setFullname(newUser.getFullname());
//            client.setUsername(newUser.getUsername());
//            client.setEmail(newUser.getEmail());
////            user.setPassword(newUser.getPassword());
//            client.setPassword(hashPassword(newUser.getPassword()));
//            return clientRepository.save(client);
//        }).orElseThrow(() -> new UserNotFoundException(id));
//    }
//
//    @Override
//    public String deleteClient(Long id) {
//        Client client = clientRepository.findById(id).orElse(null);
//        if (client == null)
//            throw new UserNotFoundException(id);
//        clientRepository.deleteById(id);
//        return "User with ID " + id + " has been deleted successfully!";
//    }
//
//    @Override
//    public Client registerClient(Client client) {
//        if (clientRepository.findByUsername(client.getUsername()) != null)
//            throw new DuplicateUsernameException("Username is already taken");
//        if (clientRepository.findByEmail(client.getEmail()) != null)
//            throw new DuplicateEmailException("Email is already registered");
//        String hashedPassword = hashPassword(client.getPassword());
//        client.setPassword(hashedPassword);
//        return clientRepository.save(client);
//    }
//
//    @Override
//    public Client loginClient(String username, String password) {
//        Client client = clientRepository.findByUsername(username);
//        if (client == null)
//            return null;
//        if (verifyPassword(password, client.getPassword()))
//            return client;
//        else
//            return null;
//    }

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
