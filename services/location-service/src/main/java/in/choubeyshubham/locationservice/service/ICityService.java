package in.choubeyshubham.locationservice.service;

import in.choubeyshubham.payload.request.CityRequest;
import in.choubeyshubham.payload.response.CityResponse;
import org.hibernate.query.Page;

import java.awt.print.Pageable;

public interface ICityService {

    CityResponse createCity(CityRequest request);
    CityResponse getCityById(Long id);
    CityResponse updateCity(Long id, CityRequest request);
    void deleteCity(Long id);
    Page<CityResponse> getAllCities(Pageable pageable);



}
