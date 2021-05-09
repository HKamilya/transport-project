package ru.kpfu.itis.transportprojectweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.transportprojectapi.dto.FlightDto;
import ru.kpfu.itis.transportprojectapi.service.FlightService;


@Controller
public class ScheduleController {
    @Autowired
    private FlightService flightService;

    @GetMapping("/schedule")
    public String getSchedule(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestParam("city") String city, Model model, @PageableDefault(sort = {"dateTimeDep"}, direction = Sort.Direction.ASC) Pageable pageable) {
        String role = "";
        if (userDetails != null) {
            role = userDetails.getAuthorities().stream().findFirst().toString();
            model.addAttribute("username", userDetails.getUsername());
        }
        Page<FlightDto> list = flightService.findByCity(city, pageable);
        System.out.println(role);
        model.addAttribute("role", role);

        model.addAttribute("page", list);
        System.out.println(list.getTotalElements());
        model.addAttribute("url", "/schedule/" + city);

        return "schedule";
    }


}
