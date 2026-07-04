package in.choubeyshubham.payload.request;

import in.choubeyshubham.embeddable.Address;
import in.choubeyshubham.embeddable.GeoCode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZoneId;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirportRequest {

    @NotBlank(message = "IATA Code is mandatory")
    @Size(min = 3, max = 3,message = "IATA code must be exactly 3 character")
    private String iataCode;

    @NotBlank(message = "Airport name is mandatory")
    private String name;

    private ZoneId timeZone;

    @Valid
    private Address address;

    @NotNull(message = "City Id is mandatory")
    private Long cityId;

    @Valid
    private GeoCode geoCode;




}
