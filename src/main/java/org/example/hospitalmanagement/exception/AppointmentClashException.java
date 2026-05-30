package org.example.hospitalmanagement.exception;

public class AppointmentClashException extends RuntimeException {
    public AppointmentClashException(String message) {
        super(message);
    }
}
