package in.choubeyshubham.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Support {

    private String email;
    private String phone;
    private String hours;
}
