package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class ProfileRestController {
    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public UserDto getUserByEmail(@PathVariable("email") String email) {
        Optional<UserDto> userDtoOptional = userService.findByEmail(email);
        if (userDtoOptional.isPresent()) {
            userDtoOptional.get().setPassword(null);
            return userDtoOptional.get();
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "success";
    }

//    @PutMapping
//    public void updateUserData(@RequestBody UserDto userDto) {
//        userService.save(userDto);
//    }
}
