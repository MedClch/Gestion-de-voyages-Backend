package com.example.projet.Model;

public class Admin extends Utilisateur {
    private static Admin ADMIN = new Admin();

    public Admin() {
        super();
        setFullname("admin");
        setPassword("12345");
        setUsername("Admin");
        setEmail("Admin");
    }

    public static Admin getInstance() {
        return ADMIN;
    }
}
