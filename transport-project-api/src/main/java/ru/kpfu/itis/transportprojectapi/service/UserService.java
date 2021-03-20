package ru.kpfu.itis.transportprojectapi.service;

public interface UserService<UserDto, Long> {

    public void signUp(UserDto user);
}
