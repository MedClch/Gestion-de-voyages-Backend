package com.example.projet.Repositories;

import com.example.projet.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByEmail(String email);
    Client findByUsername(String username);
}
