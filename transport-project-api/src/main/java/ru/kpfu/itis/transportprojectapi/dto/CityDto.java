package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CityDto {
    private Long id;
    private String city;
    private String country;
}
