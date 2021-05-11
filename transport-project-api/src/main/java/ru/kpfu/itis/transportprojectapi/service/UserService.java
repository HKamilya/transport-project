package ru.kpfu.itis.transportprojectapi.service;


import ru.kpfu.itis.transportprojectapi.dto.UserDto;

import java.util.Optional;

public interface UserService<UserD, Long> {


    public void signUp(UserDto user);


    public void save(UserDto userDto);

    public Optional<UserDto> findByEmail(String email);


    public void signUpAfterOAuth(String email, String name, String lastname, String provider);


    public void updateUserAfterOAuth(UserDto userDto, String name, String toString);


    public UserDto findByEmailOrUsername(String email);


    Optional<UserDto> findByUsername(String username);

    void deleteById(Long id);

}
