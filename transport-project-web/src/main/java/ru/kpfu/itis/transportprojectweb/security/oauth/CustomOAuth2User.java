package ru.kpfu.itis.transportprojectweb.security.oauth;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import ru.kpfu.itis.transportprojectweb.security.details.UserDetailsImpl;

import java.util.Collection;
import java.util.Map;

@ToString
public class CustomOAuth2User implements OAuth2User, UserDetails {

    private OAuth2User oAuth2User;

    public CustomOAuth2User(OAuth2User oAuth2User) {
        this.oAuth2User = oAuth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oAuth2User.getAuthorities();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return oAuth2User.getAttribute("given_name");
    }

    public String getLastname() {
        return oAuth2User.getAttribute("family_name");
    }

    public String getEmail() {
        return oAuth2User.getAttribute("email");
    }
}
