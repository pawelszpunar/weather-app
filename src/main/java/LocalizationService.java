public class LocalizationService {

    private LocalizationRepository localizationRepository = new LocalizationRepository();

    public LocalizationService() {
    }

    public Localization createNewLocalization(String city, Integer longitude, Integer latitude, String region, String country) {
        if (city == null || city.isEmpty()) {
            throw new RuntimeException("City nie moze byc pusty");
        }

        if (longitude == null) {
            throw new RuntimeException("longitude nie moze byc pusty");
        }

        if (latitude == null) {
            throw new RuntimeException("latitude nie moze byc pusty");
        }

        if (country == null) {
            throw new RuntimeException("country nie moze byc pusty");
        }

        Localization localization = new Localization(city, longitude, latitude, region, country);

        return localization;

    }


}
