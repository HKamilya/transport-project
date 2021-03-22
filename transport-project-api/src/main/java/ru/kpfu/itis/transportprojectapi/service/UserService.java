package ru.kpfu.itis.transportprojectapi.service;

import ru.kpfu.itis.transportprojectapi.dto.UserDto;

public interface UserService<UserDto, Long> {

    public void signUp(UserDto user);

}
