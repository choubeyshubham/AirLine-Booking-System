package in.choubeyshubham.seatservice.mapper;

import in.choubeyshubham.payload.request.CabinClassRequest;
import in.choubeyshubham.payload.response.CabinClassResponse;
import in.choubeyshubham.seatservice.model.CabinClass;
import in.choubeyshubham.seatservice.model.SeatMap;

public class CabinClassMapper {

    public static CabinClass toEntity(CabinClassRequest request) {
        if(request == null) return null;

        return CabinClass.builder()
                .name(request.getName())
                .code(request.getCode().toUpperCase())
                .description(request.getDescription())
                .aircraftId(request.getAircraftId())
                .displayOrder(request.getDisplayOrder() != null ? request.getDisplayOrder() : 0)
                .isActive(request.getIsActive() == null || request.getIsActive())
                .isBookable(request.getIsBookable() == null || request.getIsBookable())
                .typicalSeatPitch(request.getTypicalSeatPitch())
                .typicalSeatWidth(request.getTypicalSeatWidth())
                .seatType(request.getSeatType())
                .build();
    }

    public static CabinClassResponse toResponse(CabinClass cabinClass, SeatMap seatMap) {
        if (cabinClass == null) return null;
        return CabinClassResponse.builder()
                .id(cabinClass.getId())
                .name(cabinClass.getName().name())
                .code(cabinClass.getCode())
                .description(cabinClass.getDescription())
                .aircraftId(cabinClass.getAircraftId())

                .seatMap(seatMap != null ? SeatMapMapper.toResponse(seatMap) : null)
                .displayOrder(cabinClass.getDisplayOrder())
                .isActive(cabinClass.getIsActive())
                .isBookable(cabinClass.getIsBookable())
                .typicalSeatPitch(cabinClass.getTypicalSeatPitch())
                .typicalSeatWidth(cabinClass.getTypicalSeatWidth())
                .seatType(cabinClass.getSeatType())
                .createdAt(cabinClass.getCreatedAt())
                .updatedAt(cabinClass.getUpdatedAt())
                .build();
    }

    public static void updateEntity(CabinClassRequest request, CabinClass existing) {
        if (request == null || existing == null) return;
        if (request.getName() != null) existing.setName(request.getName());
        if (request.getCode() != null) existing.setCode(request.getCode().toUpperCase());
        if (request.getDescription() != null) existing.setDescription(request.getDescription());
        if (request.getDisplayOrder() != null) existing.setDisplayOrder(request.getDisplayOrder());
        if (request.getIsActive() != null) existing.setIsActive(request.getIsActive());
        if (request.getIsBookable() != null) existing.setIsBookable(request.getIsBookable());
        if (request.getTypicalSeatPitch() != null) existing.setTypicalSeatPitch(request.getTypicalSeatPitch());
        if (request.getTypicalSeatWidth() != null) existing.setTypicalSeatWidth(request.getTypicalSeatWidth());
        if (request.getSeatType() != null) existing.setSeatType(request.getSeatType());
    }
}

