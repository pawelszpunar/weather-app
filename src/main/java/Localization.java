import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity(name = "localization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "city") // todo you can use this -> nullable = false
    private String city;
    @Column(name = "longitude")
    private Integer longitude;
    @Column(name = "latitude")
    private Integer latitude;
    @Column(name = "region")
    private String region;
    @Column(name = "country")
    private String country;

    public Localization(String city, Integer longitude, Integer latitude, String region, String country) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.region = region;
        this.country = country;
    }
}
