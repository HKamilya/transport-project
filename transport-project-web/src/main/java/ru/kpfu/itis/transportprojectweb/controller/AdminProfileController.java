package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.transportprojectapi.dto.UserDto;
import ru.kpfu.itis.transportprojectapi.service.AdminService;
import ru.kpfu.itis.transportprojectapi.service.UserService;
import ru.kpfu.itis.transportprojectweb.security.details.UserDetailsImpl;

@Controller
public class AdminProfileController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/adminProfile")
    public String getAdminProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("id", userDetails.getId());
        model.addAttribute("role", "admin");
        return "adminProfilePage";
    }

    @PostMapping("/admin/adminProfile/delete")
    public String getAdminProfile(@RequestParam("id") Long id) {
        userService.deleteById(id);
        return "redirect:/logout";
    }

    @GetMapping("/admin/admins")
    public String getAddAdmins(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("role", "admin");
        return "adminsPage";
    }

    @PostMapping("/admin/admins")
    public String addAdmins(@RequestBody UserDto userForm) {
        adminService.addNewAdmin(userForm);
        return "adminsPage";
    }
}
