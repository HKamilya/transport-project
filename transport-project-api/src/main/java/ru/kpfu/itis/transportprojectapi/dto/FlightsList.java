package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class FlightsList {
    private List<FlightDto> flights;
    private double distance;
    private double price;
    private Long time;
}
