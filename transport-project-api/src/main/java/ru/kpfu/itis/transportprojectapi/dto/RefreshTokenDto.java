package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class RefreshTokenDto {
    private long id;
    private UserDto user;

    private String token;

    private Instant expiryDate;
}
