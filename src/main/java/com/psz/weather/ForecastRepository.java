package com.psz.weather;

import java.time.LocalDate;
import java.util.Optional;

public interface ForecastRepository {

    Optional<Forecast> getForecastByLocationAndDate(Location location, LocalDate localDate);
    Forecast saveForecast(Forecast forecast);
}
