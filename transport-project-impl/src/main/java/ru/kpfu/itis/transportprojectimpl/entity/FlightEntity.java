package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "flight")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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
