package com.psz.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ForecastController {

    private final ForecastService forecastService;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule());

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
