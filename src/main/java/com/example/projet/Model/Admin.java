package com.example.projet.Model;

public class Admin extends Utilisateur {
    private static Admin ADMIN = new Admin();

    public Admin() {
        super();
        setFullname("Admin");
        setPassword("12345");
        setUsername("admin");
        setEmail("admin@admin.com");
    }

    public static Admin getInstance() {
        return ADMIN;
    }
}
