package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String username;
    private String password;
}
