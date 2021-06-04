package com.psz.weather;

import java.time.LocalDate;
import java.util.Optional;

public class ForecastRepositoryMock implements ForecastRepository{
    @Override
    public Optional<Forecast> getForecastByLocationAndDate(Location location, LocalDate localDate) {
        return Optional.empty();
    }

    @Override
    public Forecast saveForecast(Forecast forecast) {
        return null;
    }
}
