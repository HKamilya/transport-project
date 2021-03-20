package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;

import javax.annotation.security.PermitAll;

@Controller
public class SignUpController {
    @Autowired
    private UserService userService;

    @PermitAll
    @GetMapping("/signUp")
    public String signUpPage() {
        return "signUpPage";
    }

    @PermitAll
    @PostMapping("/signUp")
    public String signUp(UserDto userDto) {
        System.out.println(userDto);
        userService.signUp(userDto);
        return "redirect:/profile";
    }
}
