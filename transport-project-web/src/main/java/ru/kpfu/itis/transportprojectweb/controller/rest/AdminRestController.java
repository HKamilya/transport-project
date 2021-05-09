package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public String postAdmins(@RequestBody UserDto userForm) {
        adminService.addNewAdmin(userForm);
        return "success";
    }

    @DeleteMapping("{id}")
    public String deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdminOrUser(id);
        return "success";
    }

}
