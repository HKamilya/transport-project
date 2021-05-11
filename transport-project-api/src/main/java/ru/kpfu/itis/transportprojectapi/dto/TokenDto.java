package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    String token;
    String refreshToken;
}
