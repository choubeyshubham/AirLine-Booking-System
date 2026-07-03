package in.choubeyshubham.locationservice.service.implementation;

import in.choubeyshubham.locationservice.mapper.CityMapper;
import in.choubeyshubham.locationservice.model.City;
import in.choubeyshubham.locationservice.repository.CityRepository;
import in.choubeyshubham.locationservice.service.ICityService;
import in.choubeyshubham.payload.request.CityRequest;
import in.choubeyshubham.payload.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements ICityService {

    private final CityRepository cityRepository;


    @Override
    public CityResponse createCity(CityRequest request) throws Exception{
//        validateCityRequest(request);

        if (cityRepository.existsByCityCode(request.getCityCode())) {
            throw new Exception("City with code " + request.getCityCode() + " already exists");
        }

        City city = CityMapper.toEntity(request);
        City savedCity = cityRepository.save(city);

//        log.info("City created: {} ({})", savedCity.getName(), savedCity.getCityCode());
        return CityMapper.toResponse(savedCity);
    }

    @Override
    public CityResponse getCityById(Long id) throws Exception {
       City city =cityRepository.findById(id).orElseThrow(
               ()-> new Exception("City not exist with given id")
       );
       return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) throws Exception {
        City city =  cityRepository.findById(id).orElseThrow(
                ()-> new Exception("City not exist with given id"));

        if(cityRepository.existsByCityCode(request.getCityCode())){
            throw new Exception("City with given code already exist");
        }

        City updatedCity = cityRepository.save(CityMapper.updateEntity(city, request));
        return CityMapper.toResponse(updatedCity);
    }

    @Override
    public void deleteCity(Long id) throws Exception{
        City city =  cityRepository.findById(id).orElseThrow(
                ()-> new Exception("City not exist with given id"));

        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable).map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }


}
