package in.choubeyshubham.flightOpsService.mapper;


import in.choubeyshubham.flightOpsService.model.Flight;
import in.choubeyshubham.flightOpsService.model.FlightSchedule;
import in.choubeyshubham.payload.request.FlightScheduleRequest;
import in.choubeyshubham.payload.response.AirportResponse;
import in.choubeyshubham.payload.response.FlightScheduleResponse;

public class FlightScheduleMapper {

    public static FlightSchedule toEntity(
            FlightScheduleRequest request,
            Flight flight
    ) {
        if (request == null || flight == null) return null;

        return FlightSchedule.builder()
                .flight(flight)
                .departureAirportId(flight.getDepartureAirportId())
                .arrivalAirportId(flight.getArrivalAirportId())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .operatingDays(request.getOperatingDays())
                .isActive(request.getIsActive() == null || request.getIsActive())
                .build();
    }

    public static FlightScheduleResponse toResponse(FlightSchedule fs,
                                                    AirportResponse arrival,
                                                    AirportResponse departure) {
        if (fs == null) return null;
        return FlightScheduleResponse.builder()
                .id(fs.getId())
                .flightId(fs.getFlight() != null ? fs.getFlight().getId() : null)
                .flightNumber(fs.getFlight() != null ? fs.getFlight().getFlightNumber() : null)
                .departureAirport(departure)
                .arrivalAirport(arrival)
                .departureTime(fs.getDepartureTime())
                .arrivalTime(fs.getArrivalTime())
                .startDate(fs.getStartDate())
                .endDate(fs.getEndDate())

                .operatingDays(fs.getOperatingDays())
                .isActive(fs.getIsActive())
                .build();
    }

    public static void updateEntity(FlightScheduleRequest request, FlightSchedule existing) {
        if (request == null || existing == null) return;
        if (request.getDepartureTime() != null) existing.setDepartureTime(request.getDepartureTime());
        if (request.getArrivalTime() != null) existing.setArrivalTime(request.getArrivalTime());
        if (request.getStartDate() != null) existing.setStartDate(request.getStartDate());
        if (request.getEndDate() != null) existing.setEndDate(request.getEndDate());
        if (request.getOperatingDays() != null) existing.setOperatingDays(request.getOperatingDays());
        if (request.getIsActive() != null) existing.setIsActive(request.getIsActive());
    }
}

