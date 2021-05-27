import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "localization")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localization {

    @Id
    @GeneratedValue
    private Integer id;







}
