package org.example.hospitalmanagement.security.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

    @NotBlank(message = "Email is required")
    @Email(message="Password is required")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
