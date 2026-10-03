package in.choubeyshubham.seatservice.mapper;


import in.choubeyshubham.enums.SeatAvailabilityStatus;
import in.choubeyshubham.payload.response.SeatInstanceResponse;
import in.choubeyshubham.seatservice.model.SeatInstance;

public class SeatInstanceMapper {

    public static SeatInstanceResponse toResponse(SeatInstance si){
        return SeatInstanceResponse.builder()
                .id(si.getId())
                .flightId(si.getFlightId())
                .seatId(si.getSeat() != null ? si.getSeat().getId() : null)
                .seatNumber(si.getSeat() != null ? si.getSeat().getSeatNumber() : null)
                .seatType(si.getSeat() != null ? si.getSeat().getSeatType().name() : null)
                .seatPosition(si.getSeat() != null ? si.getSeat().getFullPosition() : null)
                .seat(SeatMapper.toResponse(si.getSeat()))
                .status(si.getStatus())
                .flightInstanceId(si.getFlightInstanceId())
                .flightCabinId(si.getFlightInstanceCabin() != null ? si.getFlightInstanceCabin().getId() : null)
                .fare(si.getFare())
                .price(si.getPremiumSupercharge())
                .version(si.getVersion())
                .createdAt(si.getCreatedAt())
                .updatedAt(si.getUpdatedAt())
                .isAvailable(si.isAvailable())
                .isBooked(si.isBooked())
                .isOccupied(si.getStatus() == SeatAvailabilityStatus.OCCUPIED)
//                .seatCharacteristics(si.getSeat() != null ? si.getSeat().getSeatCharacteristics() : null)
                .build();
    }
}

