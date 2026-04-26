package com.proiect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAdminRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 8, message = "Parola trebuie sa aiba cel puțin 8 caractere")
    private String password;
}