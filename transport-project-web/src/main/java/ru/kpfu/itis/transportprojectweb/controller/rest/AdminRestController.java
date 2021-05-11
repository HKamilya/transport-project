package ru.kpfu.itis.transportprojectweb.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;

@RestController
@RequestMapping("/api/admins")
public class AdminRestController {
    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<?> postAdmins(@RequestBody UserDto userForm) {
        adminService.addNewAdmin(userForm);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") Long id) {
        adminService.deleteAdminOrUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
