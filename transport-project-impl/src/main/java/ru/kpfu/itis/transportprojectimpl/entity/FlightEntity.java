package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Data;

import javax.persistence.*;

import java.time.LocalTime;
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
    @Temporal(TemporalType.DATE)
    private Date dateDep;
    @Temporal(TemporalType.DATE)
    private Date dateArr;
    private LocalTime timeDep;
    private LocalTime timeArr;
    private int countOfPlaces;
    private float price;
}
