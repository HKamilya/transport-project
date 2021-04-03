package ru.kpfu.itis.transportprojectweb.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PatchMapping
    public void updateUserData(@RequestBody UserDto userDto) {
        userService.save(userDto);
    }
}
