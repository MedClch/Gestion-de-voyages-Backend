package com.example.projet.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idc;
    @Column(nullable = false)
    private String fullname;
    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false,length = 300)
    private String password;
    @OneToMany(mappedBy = "client")
    private List<Ticket> tickets;
//    @Column(nullable = false,length = 300)
//    private String hashed_password;
}
