package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@ToString
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
    private Long distance;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    public List<ReservationEntity> reservations;
}
