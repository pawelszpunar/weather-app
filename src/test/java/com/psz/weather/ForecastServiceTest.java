package com.psz.weather;

import org.junit.jupiter.api.Test;

public class ForecastServiceTest {

    @Test
    public void test() {
        LocationRepositoryMock locationRepositoryMock = new LocationRepositoryMock();
        ForecastRepositoryMock forecastRepositoryMock = new ForecastRepositoryMock();
        locationRepositoryMock.save(new Location(null, "Cracow", 45.2F, 12.1F, "", "Poland"));
        ForecastService forecastService = new ForecastService(forecastRepositoryMock, locationRepositoryMock);
        Forecast forecast = forecastService.getForecast(1, 1);
        System.out.println(forecast);
    }
}
