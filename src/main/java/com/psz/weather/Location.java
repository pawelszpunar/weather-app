package com.psz.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "localization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city")
    private String city;
    @Column(name = "longitude")
    private Float longitude;
    @Column(name = "latitude")
    private Float latitude;
    @Column(name = "region")
    private String region;
    @Column(name = "country")
    private String country;

    public Location(String city, Float longitude, Float latitude, String region, String country) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.country = country;
    }
}
