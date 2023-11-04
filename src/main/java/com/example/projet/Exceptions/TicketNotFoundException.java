package com.example.projet.Exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Couldn't find the ticket with ID "+id);
    }
}
