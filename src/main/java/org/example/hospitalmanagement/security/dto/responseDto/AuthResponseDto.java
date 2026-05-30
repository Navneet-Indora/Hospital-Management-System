package org.example.hospitalmanagement.security.dto.responseDto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthResponseDto
{
    private String token;   //JWT Token
    private String email;   //user email
    private String role;    //user role
    private String message; //success message
}
