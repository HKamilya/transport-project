package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;

@Data
public class FlightForm {
    private Long id;
    private String countryTo;
    private String cityTo;
    private String countryFrom;
    private String cityFrom;
    private String airportFrom;
    private String airportTo;
    private String planeType;
    private String dateTimeDep;
    private String dateTimeArr;
    private int countOfPlaces;
    private String state;
    private int price;
}
