package ru.kpfu.itis.transportprojectweb.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.itis.transportprojectapi.dto.CityDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.dto.FlightForm;
import ru.kpfu.itis.transportprojectapi.service.CityService;
import ru.kpfu.itis.transportprojectapi.service.FlightService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminProfileController {

    @GetMapping("/admin/adminProfile")
    public String getAdminProfile() {
        return "adminProfilePage";
    }

}
