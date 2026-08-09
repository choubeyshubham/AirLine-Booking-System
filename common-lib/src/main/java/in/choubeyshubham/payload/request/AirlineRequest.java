package in.choubeyshubham.payload.request;

import in.choubeyshubham.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineRequest {

    @NotBlank
    @Size(min = 2, max = 2, message = "IATA code must be exactly 2 characters")
    private String iataCode;

    @NotBlank
    @Size(min = 3, max = 3, message = "ICAO code must be exactly 3 characters")
    private String icaoCode;

    @NotBlank
    private String name;

    private String alias;

    @NotBlank
    private String country;

    private String logoUrl;

    private String website;

    private AirlineStatus status;

    private String alliance;

    private Long headquartersCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHours;
}
