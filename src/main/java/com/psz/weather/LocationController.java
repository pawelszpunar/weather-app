package com.psz.weather;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LocationController {

    private final LocationService locationService;
    private ObjectMapper objectMapper = new ObjectMapper();

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
}
