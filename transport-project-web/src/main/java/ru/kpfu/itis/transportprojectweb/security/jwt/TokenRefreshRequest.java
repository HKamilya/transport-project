package ru.kpfu.itis.transportprojectweb.security.jwt;

import lombok.Data;


@Data
public class TokenRefreshRequest {
    private String refreshToken;
}
