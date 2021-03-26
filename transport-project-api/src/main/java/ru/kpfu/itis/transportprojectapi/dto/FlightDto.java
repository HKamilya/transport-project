package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class FlightDto {
    private String countryTo;
    private String cityTo;
    private String airportTo;
    private String countryFrom;
    private String cityFrom;
    private String airportFrom;
    private String planeType;
    private String dateDep;
    private String dateArr;
    private String timeDep;
    private String timeArr;
    private int countOfPlaces;
    private float price;
}
