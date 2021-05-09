package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;

@Data
public class PlaneDto {
    private Long id;

    private String name;
    private Long speed;
    private int countOfPlaces;
}
