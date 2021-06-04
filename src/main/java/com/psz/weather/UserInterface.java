package com.psz.weather;

import java.util.Scanner;

public class UserInterface {
    private final LocationController locationController;
    private final ForecastController forecastController;


    public UserInterface(LocationController locationController, ForecastController forecastController) {
        this.locationController = locationController;
        this.forecastController = forecastController;
    }

    public void run() {
        System.out.println("The application is launched!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Weather app, what do you want to do?");
            System.out.println("1. Add a new location");
            System.out.println("2. Show available locations");
            System.out.println("3. Get weather forecast");
            System.out.println("0. Close the application");
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    addNewLocation();
                    break;
                case 2:
                    getAllLocations();
                    break;
                case 3:
                    getForecast();
                case 0:
                    return;
            }
        }
    }

    private void addNewLocation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("Longitude: ");
        Float longitude = scanner.nextFloat();
        System.out.print("Latitude: ");
        Float latitude = scanner.nextFloat();
        System.out.print("Region (optional): ");
        scanner.nextLine();
        String region = scanner.nextLine();
        System.out.print("Country: ");
        String country = scanner.nextLine();

        String httpResponse = locationController.createNewLocalization(city, longitude, latitude, region, country);
        System.out.println("Server response: " + httpResponse);
    }

    private void getAllLocations() {
        String result = locationController.getAllLocations();
        System.out.println("Available locations:\n" + result);
    }

    private void getForecast() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Location id: ");
        Integer locationId = scanner.nextInt();
        System.out.println("Date (0 - today, 1 - tomorrow ... etc up to 7): ");
        Integer date = scanner.nextInt();
        String result = forecastController.getForecast(locationId, date);
        System.out.println("Server response: " + result);
    }
}
