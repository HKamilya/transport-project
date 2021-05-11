package ru.kpfu.itis.transportprojectweb.security.jwt;

import com.auth0.jwt.exceptions.TokenExpiredException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JwtProvider {
    @Autowired
    @Qualifier("customUserDetailService")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtProperties jwtProperties;

    public String generateToken(String login, String role) {
        Date date = Date.from(Instant.now().plusMillis(jwtProperties.getValidity()));
        Claims claims = Jwts.claims().setSubject(login);
        claims.put("role", String.valueOf(role));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(login)
                .setIssuedAt(new Date())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            throw new TokenExpiredException("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            throw new UnsupportedJwtException("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            throw new MalformedJwtException("Malformed jwt");
        } catch (SignatureException sEx) {
            throw new SignatureException("Invalid signature");
        } catch (Exception e) {
            throw new TokenExpiredException("invalid token");
        }
    }

    public String getLoginFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token).getBody().getSubject();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getLoginFromToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
