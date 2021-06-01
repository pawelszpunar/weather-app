package com.psz.weather;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class WeatherApplication {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        SessionFactory sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();

        LocationRepositoryImpl locationRepositoryImpl = new LocationRepositoryImpl(sessionFactory);
        LocationService locationService = new LocationService(locationRepositoryImpl);
        LocationController locationController = new LocationController(locationService);

        ForecastRepositoryImpl forecastRepositoryImpl = new ForecastRepositoryImpl(sessionFactory);
        ForecastService forecastService = new ForecastService(forecastRepositoryImpl, locationRepositoryImpl);
        ForecastController forecastController = new ForecastController(forecastService);

        UserInterface userInterface = new UserInterface(locationController, forecastController);
        userInterface.run();
    }
}
