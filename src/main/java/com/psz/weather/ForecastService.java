package com.psz.weather;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

//@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;
    private final LocationRepository locationRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ForecastService(ForecastRepository forecastRepository, LocationRepository locationRepository) {
        this.forecastRepository = forecastRepository;
        this.locationRepository = locationRepository;
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }


    public Forecast getForecast(Integer locationId, Integer date) {

        Location location = locationRepository.findById(locationId)
                .orElseThrow(() -> new RuntimeException("Nie ma takiej lokalizacji o id " + locationId));
        LocalDate forecastDate = LocalDate.now().plusDays(date);

        if (forecastRepository.getForecastByLocationAndDate(location, forecastDate).isPresent()) {
            System.out.println("Forecast found in database!");
            return forecastRepository.getForecastByLocationAndDate(location, forecastDate).get();
        } else {
            System.out.println("Forecast is not in database. Connecting to server.");


            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/onecall?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&exclude=minutely,hourly&appid=1766fdc82c622688913c1bb885b9bd94"))
                    .build();
            try {
                HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ForecastResponseDTO forecastResponseDTO = objectMapper.readValue(responseBody, ForecastResponseDTO.class);

                Forecast forecast = forecastResponseDTO.getDaily().stream()
                        .filter(s -> s.getDate().equals(forecastDate))
                        .findFirst()
                        .map(s -> Forecast.builder()
                                .temperature(s.getTemperature().getCelsius())
                                .pressure(s.getPressure())
                                .windSpeed(s.getWindSpeed())
                                .windDeg(s.getWindDeg())
                                .humidity(s.getHumidity())
                                .location(location)
                                .build())
                        .orElseThrow(() -> new RuntimeException("No weather found for the given date!"));

                forecast.setLocalDate(forecastDate);
                forecastRepository.saveForecast(forecast);

                return forecast;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
