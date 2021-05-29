package com.psz.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationService {

    private final LocationRepository locationRepository;   // todo use DependencyInversion <- DONE
    private ObjectMapper objectMapper = new ObjectMapper();

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public Location createNewLocation(String city, Float longitude, Float latitude, String region, String country) {
        if (city == null || city.isBlank()) { // todo "   " .isBlank()
            throw new RuntimeException("City nie moze byc pusty");
        }
        if (longitude == null || longitude > 180.0 || longitude < -180.0) {    // todo extend this validation -180 180
            throw new RuntimeException("longitude nie moze byc pusty");
        }
        if (latitude == null || latitude > 90 || latitude < -90) {     // todo extend this validation -90 90
            throw new RuntimeException("latitude nie moze byc pusty");
        }
        if (country == null || country.isBlank()) {  // todo you can use isBlank() method
            throw new RuntimeException("country nie moze byc pusty");
        }

        Location location = new Location(null, city, longitude, latitude, null, country);
        if(region.isBlank()) {
            location.setRegion(null);
        } else {
            location.setRegion(region);
        }

        // todo if region is null or blank ("    ") don't save that value
        // todo you have to save location to your database, so you can use localizationRepository

        return locationRepository.save(location);
    }
}
