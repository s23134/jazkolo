package nbp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class SaveData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String currency;
    private String startDate;
    private String endDate;

    public SaveData( String currency, String startDate, String endDate) {
        this.currency = currency;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
