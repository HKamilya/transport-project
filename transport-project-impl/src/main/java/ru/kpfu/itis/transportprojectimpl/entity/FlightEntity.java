package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "flight")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private CityEntity cityTo;
    @ManyToOne
    private CityEntity cityFrom;
    private String airportFrom;
    private String airportTo;
    private String planeType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeDep;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeArr;
    private int countOfPlaces;
    private double distance;
    private int price;

    @Enumerated(EnumType.STRING)
    private State state;

    @ToString.Exclude
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    public List<ReservationEntity> reservations;

    public enum State {
        ACTIVE, CANCELED
    }

}
