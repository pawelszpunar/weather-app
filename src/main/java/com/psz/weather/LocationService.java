package com.psz.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LocationService {

    private final LocationRepository locationRepository;
    private ObjectMapper objectMapper = new ObjectMapper();

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public Location createNewLocation(String city, Float longitude, Float latitude, String region, String country) {
        if (city == null || city.isBlank()) {
            throw new RuntimeException("City nie moze byc pusty");
        }
        if (longitude == null || longitude > 180.0 || longitude < -180.0) {
            throw new RuntimeException("longitude nie moze byc pusty");
        }
        if (latitude == null || latitude > 90 || latitude < -90) {
            throw new RuntimeException("latitude nie moze byc pusty");
        }
        if (country == null || country.isBlank()) {
            throw new RuntimeException("country nie moze byc pusty");
        }

        Location location = new Location(null, city, longitude, latitude, null, country);
        if(region.isBlank()) {
            location.setRegion(null);
        } else {
            location.setRegion(region);
        }

        return locationRepository.save(location);
    }

    public List<Location> getAllLocations() {
        List<Location> locations = locationRepository.getLocations();
        if(locations.isEmpty()) {
            throw new RuntimeException("Database is empty!");
        }
        return locations;
    }
}
