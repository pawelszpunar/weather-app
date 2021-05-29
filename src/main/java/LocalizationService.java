public class LocalizationService {

    private LocalizationRepository localizationRepository = new LocalizationRepository();   // todo use DependencyInversion

    public LocalizationService() {
    }

    public Localization createNewLocalization(String city, Integer longitude, Integer latitude, String region, String country) {
        if (city == null || city.isEmpty()) { // todo "   " .isBlank()
            throw new RuntimeException("City nie moze byc pusty");
        }
        if (longitude == null) {    // todo extend this validation -180 180
            throw new RuntimeException("longitude nie moze byc pusty");
        }
        if (latitude == null) {     // todo extend this validation -90 90
            throw new RuntimeException("latitude nie moze byc pusty");
        }
        if (country == null) {  // todo you can use isBlank() method
            throw new RuntimeException("country nie moze byc pusty");
        }

        Localization localization = new Localization(city, longitude, latitude, region, country);

        // todo if region is null or blank ("    ") don't save that value
        // todo you have to save location to your database, so you can use localizationRepository

        return localization;
    }
}
