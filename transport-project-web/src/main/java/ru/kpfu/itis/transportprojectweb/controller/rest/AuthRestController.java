package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.AuthenticationDto;
import ru.kpfu.itis.transportprojectapi.dto.TokenDto;
import ru.kpfu.itis.transportprojectapi.service.JwtService;
import ru.kpfu.itis.transportprojectimpl.entity.RefreshTokenEntity;
import ru.kpfu.itis.transportprojectimpl.exception.TokenRefreshException;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthenticationDto authDto) {
        return ResponseEntity.ok(jwtService.signIn(authDto));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshAccessToken(@RequestParam("refresh") String refresh) {

        String newToken = jwtService.getNewAccessToken(refresh);
        return ResponseEntity.ok(newToken);
    }
}


