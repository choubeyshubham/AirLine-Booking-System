package in.choubeyshubham.flightOpsService.mapper;


import in.choubeyshubham.flightOpsService.model.Flight;
import in.choubeyshubham.payload.request.FlightRequest;
import in.choubeyshubham.payload.response.AircraftResponse;
import in.choubeyshubham.payload.response.AirlineResponse;
import in.choubeyshubham.payload.response.AirportResponse;
import in.choubeyshubham.payload.response.FlightResponse;

public class FlightMapper {

    public static Flight toEntity(FlightRequest request){
        if(request == null) return null;
        return Flight.builder()
                .flightNumber(request.getFlightNumber())
                .aircraftId(request.getAircraftId())
                .departureAirportId(request.getDepartureAirportId())
                .arrivalAirportId(request.getArrivalAirportId())
                .build();
    }

    public static FlightResponse toResponse(Flight flight,
                                            AircraftResponse aircraft,
                                            AirlineResponse airlineResponse,
                                            AirportResponse departureAirport,
                                            AirportResponse arrivalAirport) {
        if (flight == null) return null;
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(airlineResponse)
                .aircraft(aircraft)
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .status(flight.getStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }

    public static void updateEntity(FlightRequest request, Flight existing) {
        if (request == null || existing == null) return;
        if (request.getFlightNumber() != null) existing.setFlightNumber(request.getFlightNumber());
        if (request.getAircraftId() != null) existing.setAircraftId(request.getAircraftId());
        if (request.getDepartureAirportId() != null) existing.setDepartureAirportId(request.getDepartureAirportId());
        if (request.getArrivalAirportId() != null) existing.setArrivalAirportId(request.getArrivalAirportId());
        if (request.getStatus() != null) existing.setStatus(request.getStatus());
    }
}
