import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "weather_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    public WeatherData(Integer temperature, Integer pressure, Integer humidity, Integer windSpeed, Integer windDegree, Integer localization_id) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.localization_id = localization_id;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "pressure")
    private Integer pressure;

    @Column(name = "humidity")
    private Integer humidity;

    @Column(name = "wind_speed")
    private Integer windSpeed;

    @Column(name = "wind_degree")
    private  Integer windDegree;

    @Column(name = "localization_id")
    private Integer localization_id;

}
