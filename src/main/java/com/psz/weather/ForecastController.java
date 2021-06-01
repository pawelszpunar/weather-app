package com.psz.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ForecastController {

    private final ForecastService forecastService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    public String getForecast(Integer locationId, Integer date) {
        try {
            Forecast forecast = forecastService.getForecast(locationId, date);
            return objectMapper.writeValueAsString(forecast);
        } catch (JsonProcessingException | RuntimeException e) {
            return "Error message: " + e.getMessage();
        }
    }

}
