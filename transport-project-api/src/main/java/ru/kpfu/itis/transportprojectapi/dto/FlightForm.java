package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FlightForm {
    private Long id;
    private String countryTo;
    private String cityTo;
    private String countryFrom;
    private String cityFrom;
    private String airportFrom;
    private String airportTo;
    private Long planeType;
    private String dateTimeDep;
    private String dateTimeArr;
    private String state;
    private int price;
}
