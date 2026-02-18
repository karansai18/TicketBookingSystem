package com.hcl.TicketBookingSystem.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
          // Constructor with message
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
