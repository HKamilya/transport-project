package ru.kpfu.itis.transportprojectweb.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import ru.kpfu.itis.transportprojectimpl.entity.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getEmail();
        String name = oAuth2User.getName();
        String lastname = oAuth2User.getLastname();
        Optional<UserDto> userDto = userService.findByEmail(email);
        if (!userDto.isPresent()) {
            userService.signUpAfterOAuth(email, name, lastname, UserEntity.AuthProvider.GOOGLE.toString());
        } else {
            userService.updateUserAfterOAuth(userDto.get(), name, UserEntity.AuthProvider.GOOGLE.toString());
        }
        if (oAuth2User.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            response.sendRedirect("/admin/adminProfile");
        } else {
            response.sendRedirect("/profile");
        }

    }
}
