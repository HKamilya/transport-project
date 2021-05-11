package ru.kpfu.itis.transportprojectapi.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReservationDto {
    private Long id;

    private FlightDto flight;

    public UserDto passenger;

    private String state;

    private Integer countOfPlaces;

}
