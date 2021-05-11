package ru.kpfu.itis.transportprojectweb.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.transportprojectapi.dto.AuthenticationDto;
import ru.kpfu.itis.transportprojectapi.dto.RefreshTokenDto;
import ru.kpfu.itis.transportprojectapi.dto.TokenDto;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.JwtService;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import com.auth0.jwt.JWT;
import ru.kpfu.itis.transportprojectimpl.entity.RefreshTokenEntity;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;
import ru.kpfu.itis.transportprojectimpl.repository.RefreshTokenRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtProperties jwtProperties;


    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TokenDto signIn(AuthenticationDto authDto) {
        UserDto user = userService.findByEmailOrUsername(authDto.getUsername());
        if (user != null) {
            String refresh = UUID.randomUUID().toString();
            RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
            refreshTokenEntity.setExpiryDate(Instant.now().plusMillis(jwtProperties.getRefreshValidity()));
            refreshTokenEntity.setUser(modelMapper.map(user, UserEntity.class));
            refreshTokenEntity.setToken(refresh);
            System.out.println(refreshTokenEntity);
            UserEntity userEntity = modelMapper.map(user, UserEntity.class);
            refreshTokenRepository.save(refreshTokenEntity);
            String token = jwtProvider.generateToken(user.getUsername(), user.getRole());
            return TokenDto.builder()
                    .refreshToken(refresh)
                    .token(token)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public boolean verifyExpiration(RefreshTokenDto token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            RefreshTokenEntity entity = modelMapper.map(token, RefreshTokenEntity.class);
            refreshTokenRepository.delete(entity);
            return false;
        }
        return true;
    }


    @Override
    public RefreshTokenDto findByToken(String token) {
        Optional<RefreshTokenEntity> entity = refreshTokenRepository.findByToken(token);
        if (entity.isPresent()) {
            return modelMapper.map(entity, RefreshTokenDto.class);
        } else {
            return null;
        }
    }

    @Override
    public String getNewAccessToken(String token) {
        RefreshTokenDto refreshTokenDto = findByToken(token);
        if (refreshTokenDto != null) {
            if (verifyExpiration(refreshTokenDto)) {
                return jwtProvider.generateToken(refreshTokenDto.getUser().getUsername(), refreshTokenDto.getUser().getRole());
            }
        }
        throw new UsernameNotFoundException("Invalid refreshToken please make signIn ");
    }
}
