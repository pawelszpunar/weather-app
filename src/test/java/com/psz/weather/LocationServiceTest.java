package com.psz.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class LocationServiceTest {

    LocationService locationService;

    @BeforeEach
    public void setUp() {
        LocationRepository locationRepository = new LocationRepositoryMock();
        locationService = new LocationService(locationRepository);
    }

    @Test
    public void whenCreateNewLocation_givenCorrectValues_thenCreateNewLocation() {
        //when
        Location location = locationService.createNewLocation("city", 50.0F, 60.0F, "region", "country");
        //then
        assertThat(location.getId()).isNotNull();
        assertThat(location.getCity()).isEqualTo("city");
        assertThat(location.getLongitude()).isEqualTo(50.0F);
        assertThat(location.getLatitude()).isEqualTo(60.0F);
        assertThat(location.getRegion()).isEqualTo("region");
        assertThat(location.getCountry()).isEqualTo("country");
    }

    @Test
    public void whenCreateNewLocation_givenEmptyCity_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("", 50.0F, 60.0F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenNullCity_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation(null, 50.0F, 60.0F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenBlankCity_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("      ", 50.0F, 60.0F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenEmptyCountry_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 50.0F, 60.0F, "region", ""));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenNullCountry_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 50.0F, 60.0F, "region", null));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenBlankCountry_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 50.0F, 60.0F, "region", "     "));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooHighLatitude_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 50.0F, 91F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooLowLatitude_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 50.0F, -91F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooHighLongitude_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 190F, 80F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenTooLowLongitude_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", -190F, 80F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }


    @Test
    public void whenCreateNewLocation_givenNullLongitude_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", null, 80F, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenNullLatitude_thenCreateNewLocation() {
        //when
        Throwable location = catchThrowable(() -> locationService
                .createNewLocation("city", 90F, null, "region", "country"));
        //then
        assertThat(location).isExactlyInstanceOf(RuntimeException.class);
    }

    @Test
    public void whenCreateNewLocation_givenBlankRegion_thenCreateNewLocation() {
        //when
        Location location = locationService.createNewLocation("city", 50.0F, 60.0F, "    ", "country");
        //then
        assertThat(location.getId()).isNotNull();
        assertThat(location.getCity()).isEqualTo("city");
        assertThat(location.getLongitude()).isEqualTo(50.0F);
        assertThat(location.getLatitude()).isEqualTo(60.0F);
        assertThat(location.getRegion()).isNull();
        assertThat(location.getCountry()).isEqualTo("country");
    }

    @Test
    public void whenCreateNewLocation_givenEmptyRegion_thenCreateNewLocation() {
        //when
        Location location = locationService.createNewLocation("city", 50.0F, 60.0F, "", "country");
        //then
        assertThat(location.getId()).isNotNull();
        assertThat(location.getCity()).isEqualTo("city");
        assertThat(location.getLongitude()).isEqualTo(50.0F);
        assertThat(location.getLatitude()).isEqualTo(60.0F);
        assertThat(location.getRegion()).isNull();
        assertThat(location.getCountry()).isEqualTo("country");
    }
}