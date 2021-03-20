package ru.kpfu.itis.transportprojectweb.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;

@Controller
public class AdminProfileController {


    @GetMapping("/adminProfile")
    public String adminPage() {
        return "adminProfile";
    }


}
