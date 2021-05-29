package com.psz.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class LocationController {

    private final LocationService locationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    public String createNewLocalization(String city, Float longitude, Float latitude, String region, String country) {
        try {
            Location newLocation = locationService.createNewLocation(city, longitude, latitude, region, country);
            return objectMapper.writeValueAsString(newLocation);
        } catch (Exception e) {
            return "error message: " + e.getMessage();
        }
    }

    public String getAllLocations() {
        try {
            List<Location> locations = locationService.getAllLocations();
            return objectMapper.writeValueAsString(locations);
        } catch (JsonProcessingException | RuntimeException e) {
            return "error message: " + e.getMessage();
        }
    }
}
