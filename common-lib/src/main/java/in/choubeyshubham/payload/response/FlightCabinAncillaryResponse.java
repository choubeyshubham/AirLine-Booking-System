package in.choubeyshubham.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightCabinAncillaryResponse {

    private Long id;
    private Long flightId;
    private Long cabinClassId;
    private AncillaryResponse ancillary;
    private Boolean available;
    private Integer maxQuantity;
    private Double price;
    private Boolean includedInFare;

}
