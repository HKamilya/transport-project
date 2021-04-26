package ru.kpfu.itis.transportprojectimpl.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@ToString
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String city;
    private String country;

    @ToString.Exclude
    @OneToMany(mappedBy = "cityTo", cascade = CascadeType.ALL)
    public List<FlightEntity> flightsTo;
    @ToString.Exclude
    @OneToMany(mappedBy = "cityFrom", cascade = CascadeType.ALL)
    public List<FlightEntity> flightsFrom;

}
