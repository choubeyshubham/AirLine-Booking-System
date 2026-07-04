package in.choubeyshubham.embeddable;


import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeoCode {
    private Double latitude;
    private Double longitude;
}
