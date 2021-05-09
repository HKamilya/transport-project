package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.AuthenticationDto;
import ru.kpfu.itis.transportprojectapi.dto.RefreshTokenDto;
import ru.kpfu.itis.transportprojectapi.dto.TokenDto;

public interface JwtService {
    TokenDto signIn(AuthenticationDto authDto);

    boolean verifyExpiration(RefreshTokenDto token);

    RefreshTokenDto findByToken(String token);

    String getNewAccessToken(String token);
}
