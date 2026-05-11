package org.example.hospitalmanagement.exception;

import lombok.*;

import java.time.LocalDateTime;

//Error response format
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;         //HTTP status code 404, 400, 500
    private String message;     //error message
    private LocalDateTime timestamp;
}
