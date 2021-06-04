package com.psz.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.time.LocalDate;

@Entity(name = "Forecast")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Forecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double windSpeed;
    private Double windDeg;
    private LocalDate localDate;

    @ManyToOne
    private Location location;

}
