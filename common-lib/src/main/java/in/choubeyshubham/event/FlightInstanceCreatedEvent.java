package in.choubeyshubham.event;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightInstanceCreatedEvent {

    private Long flightInstanceId;
    private Long aircraftId;
    private Long flightId;


}

