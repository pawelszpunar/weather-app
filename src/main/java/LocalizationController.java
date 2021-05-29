import com.fasterxml.jackson.databind.ObjectMapper;

public class LocalizationController {

    private final LocalizationService localizationService;

    public LocalizationController(LocalizationService localizationService) {
        this.localizationService = localizationService;
    }

    public String createNewLocalization(String city, Integer longitude, Integer latitude, String region, String country) {
        try {
            Localization newLocalization = localizationService.createNewLocalization(city, longitude, latitude, region, country);
            // todo return a JSON
            //  use objectMapper.writeValueAsString(newLocalization)
            return "todo return test";
        } catch (RuntimeException e) {
            return "error message: " + e.getMessage();
        }
    }
}
