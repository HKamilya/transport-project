package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class FlightDto {
    private Long id;
    private CityDto cityTo;

    private CityDto cityFrom;
    private PlaneDto planeType;
    private String airportFrom;
    private String airportTo;
    private String dateTimeDep;
    private String dateTimeArr;
    private int countOfPlaces;
    private Long distance;
    private String state;
    private int price;
}
