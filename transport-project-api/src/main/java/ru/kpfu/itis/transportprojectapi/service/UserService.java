package ru.kpfu.itis.transportprojectapi.service;


import ru.kpfu.itis.transportprojectapi.dto.UserDto;

import java.util.Optional;

public interface UserService<UserD, Long> {

    public void signUp(UserDto user);

    public void save(UserDto userDto);

    public UserDto findByEmail(String email);

    void signUpAfterOAuth(String email, String name, String lastname, String provider);

    void updateUserAfterOAuth(UserDto userDto, String name, String toString);

    UserDto findByEmailOrUsername(String email);

    Optional<UserDto> findByUsername(String username);
}
