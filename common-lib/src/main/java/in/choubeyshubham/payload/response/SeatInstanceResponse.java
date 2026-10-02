package in.choubeyshubham.payload.response;


import in.choubeyshubham.enums.CabinClassType;
import in.choubeyshubham.enums.SeatAvailabilityStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatInstanceResponse {
    private Long id;

    private Long flightId;
    private Long seatId;
    private String seatNumber;
    private String seatType;
    private String seatPosition;

    private SeatResponse seat;

    private Double price;

    private SeatAvailabilityStatus status;

    private Long flightInstanceId;

    private Boolean isBooked;

    private Long flightCabinId;
    private CabinClassType flightCabinClassType;


    private Double fare;

    private Long version;
    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isAvailable;
    private Boolean isOccupied;
    private String seatCharacteristics;
}
