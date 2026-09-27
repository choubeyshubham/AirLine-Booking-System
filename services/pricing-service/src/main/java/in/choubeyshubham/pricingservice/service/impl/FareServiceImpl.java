package in.choubeyshubham.pricingservice.service.impl;


import in.choubeyshubham.payload.request.FareRequest;
import in.choubeyshubham.payload.response.FareResponse;
import in.choubeyshubham.pricingservice.model.Fare;
import in.choubeyshubham.pricingservice.repository.FareRepository;
import in.choubeyshubham.pricingservice.service.FareService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    @Override
    public FareResponse createFare(FareRequest request) throws Exception {
        if(fareRepository.existsByFlightIdAndCabinClassIdAndName(
                request.getFlightId(),
                request.getCabinClassId(),
                request.getName()
        )){
            throw new Exception("fare already exist with provided name");
        }
        Fare fare = FareMapper.toEntity(request);
        Fare saved=fareRepository.save(fare);
        return FareMapper.toResponse(saved);
    }

    @Override
    public FareResponse getFareById(Long id) throws Exception {
        Fare fare = fareRepository.findById(id).orElseThrow(
                ()-> new Exception("Fare not found with given Id")
        );
        return FareMapper.toResponse(fare);
    }

    @Override
    public List<FareResponse> getFaresByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {
        return fareRepository.findByFlightIdAndCabinClassId(
                flightId,cabinClassId
        ).stream().map(
                FareMapper::toResponse
        ).toList();
    }

    @Override
    public FareResponse updateFare(Long id, FareRequest request) throws Exception {
        Fare fare = fareRepository.findById(id).orElseThrow(
                ()-> new Exception("Fare not found with given Id")
        );

        if(fareRepository.existsByFlightIdAndCabinClassIdAndNameAndIdNot(
                request.getFlightId(),
                request.getCabinClassId(),
                request.getName(),
                fare.getId()
        ));
        FareMapper.updateEntity(request,fare);
        Fare updated=fareRepository.save(fare);
        return FareMapper.toResponse(updated);
    }

    @Override
    public void deleteFare(Long id) throws Exception {
        Fare fare = fareRepository.findById(id).orElseThrow(
                ()-> new Exception("Fare not found with given Id")
        );
        fareRepository.delete(fare);
    }

    @Override
    public List<Fare> getFares() {
        return fareRepository.findAll();
    }
/*
 Flight 101:
  Fare 1 → ₹5000
  Fare 2 → ₹4500
  Fare 3 → ₹6000
*
flightIds    = [101, 102, 103]
cabinClassId = 2  (Economy)
*
[ Flight 101 → ₹5000
Flight 101 → ₹4500
Flight 101 → ₹6000

Flight 102 → ₹7000
Flight 102 → ₹6500 ]
*

{ 101 → Fare 4500
102 → Fare 6500
}

{
  101 → FareResponse(price=4500)
  102 → FareResponse(price=6500)
}
*/

    @Override
    public Map<Long, FareResponse> getLowestFarePerFlight(List<Long> flightIds, Long cabinClassId) {
        if(flightIds==null || flightIds.isEmpty())return Map.of();

        List<Fare> fares = fareRepository.findByFlightIdInAndCabinClassId(
                flightIds,cabinClassId
        );

        Map<Long, FareResponse> result=fares.stream()
                .collect(Collectors.toMap(
                        Fare::getFlightId,
                        fare -> fare,
                        (existing, candidate)->
                                candidate.getTotalPrice()<existing.getTotalPrice()
                                        ?candidate:existing
                )).entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e-> FareMapper.toResponse(e.getValue())
                ));

        return result;
    }

    @Override
    public FareResponse getLowestFareForFlightAndCabin(Long flightId, Long cabinClassId) {
        List<Fare> fares=fareRepository.findByFlightIdAndCabinClassId(
                flightId,cabinClassId
        );
        Fare lowestFare=fares.stream()
                .min(Comparator.comparingDouble(Fare::getTotalPrice))
                .orElseThrow(null);

        return FareMapper.toResponse(lowestFare);
    }

    @Override
    public Map<Long, FareResponse> getFaresByIds(List<Long> ids) {
        List<Fare> fares=fareRepository.findAllById(ids);

//        [fare response, fare response]

//        {
//            1 => fare response,
//            2 => fare response
//        }
        return fares.stream().collect(Collectors.toMap(Fare::getId, FareMapper::toResponse));

    }
}
