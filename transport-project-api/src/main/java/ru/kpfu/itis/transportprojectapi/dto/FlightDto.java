package ru.kpfu.itis.transportprojectapi.dto;

import java.sql.Time;
import java.util.Date;

public class FlightDto {
    private String countryTo;
    private String cityTo;
    private String airportTo;
    private String countryFrom;
    private String cityFrom;
    private String airportFrom;
    private String planeType;
    private Date date1;
    private Time time1;
    private Date date2;
    private Time time2;
    private int countOfPlaces;
}
