import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity(name = "localization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    public Localization(String city, Integer longitude, Integer latitude, String region, String country) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.country = country;
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "city")
    @NonNull
    private String city;

    @Column(name = "longitude")
    @NonNull
    private Integer longitude; //długość geograficzna

    @Column(name = "latitude")
    @NonNull
    private Integer latitude; //szerokość geograficzna

    @Column(name = "region")
    private  String region;

    @Column(name = "country")
    @NonNull
    private String country;

    @OneToMany
    @JoinColumn(name = "localization_id")
    private List<WeatherData> weatherData;

}
