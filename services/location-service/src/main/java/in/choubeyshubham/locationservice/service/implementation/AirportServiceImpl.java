package in.choubeyshubham.locationservice.service.implementation;

import in.choubeyshubham.locationservice.mapper.AirportMapper;
import in.choubeyshubham.locationservice.model.Airport;
import in.choubeyshubham.locationservice.model.City;
import in.choubeyshubham.locationservice.repository.AirportRepository;
import in.choubeyshubham.locationservice.repository.CityRepository;
import in.choubeyshubham.locationservice.service.AirportService;
import in.choubeyshubham.payload.request.AirportRequest;
import in.choubeyshubham.payload.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) throws Exception {
        if (airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new Exception("Airport with Iata Code already Exist");
        }
        City city = cityRepository.findById(request.getCityId()).orElseThrow(() -> new Exception("City Not Found"));
        Airport airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        Airport savedAirport = airportRepository.save(airport);

        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not exist with provided id")
        );
        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirportById(Long id, AirportRequest request) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not exist with the id " + id));

        if ((airport.getIataCode() != null) && (!airport.getIataCode().equals(request.getIataCode()))
                && (airportRepository.findByIataCode(request.getIataCode()).isPresent())
        ) {
            throw new Exception("Airport with Iata Code already Exist");
        }
        AirportMapper.updateEntity(request,airport);

        Airport updatedAirport=airportRepository.save(airport);
        return AirportMapper.toResponse(updatedAirport);
    }

    @Override
    public void deleteAirportById(Long id) throws Exception {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new Exception("Airport not exist with provided id")
        );
        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportsByCityId(Long cityId) {
        return airportRepository.findByCityId(cityId).stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}
