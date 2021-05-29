package com.psz.weather;

import java.util.Scanner;

public class UserInterface {
    private final LocationController locationController;

    public UserInterface(LocationController locationController) {
        this.locationController = locationController;
    }

    public void run() {
        System.out.println("The application is launched!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the Weather app, what do you want to do?");
            System.out.println("1. Add a new location");
            System.out.println("0. Close the application");
            int response = scanner.nextInt();
            switch (response) {
                case 1:
                    addNewLocation();
                    break;
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
}
