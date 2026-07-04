package in.choubeyshubham.locationservice.service;

import in.choubeyshubham.payload.request.AirportRequest;
import in.choubeyshubham.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {

    AirportResponse createAirport(AirportRequest request) throws Exception;

    AirportResponse getAirportById(Long id) throws Exception;

    List<AirportResponse> getAllAirports();

    AirportResponse updateAirportById(Long id, AirportRequest request) throws Exception;

    void deleteAirportById(Long id) throws Exception;

    List<AirportResponse> getAirportsByCityId(Long cityId);

}
