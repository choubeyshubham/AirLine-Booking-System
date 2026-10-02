package in.choubeyshubham.seatservice.service.impl;


import in.choubeyshubham.enums.SeatAvailabilityStatus;
import in.choubeyshubham.enums.SeatType;
import in.choubeyshubham.seatservice.model.CabinClass;
import in.choubeyshubham.seatservice.model.FlightInstanceCabin;
import in.choubeyshubham.seatservice.model.SeatInstance;
import in.choubeyshubham.seatservice.model.SeatMap;
import in.choubeyshubham.seatservice.repository.CabinClassRepository;
import in.choubeyshubham.seatservice.repository.FlightInstanceCabinRepository;
import in.choubeyshubham.seatservice.repository.SeatInstanceRepository;
import in.choubeyshubham.seatservice.repository.SeatMapRepository;
import in.choubeyshubham.seatservice.service.FlightInstanceCabinService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;
    private final SeatInstanceRepository seatInstanceRepository;

    @Override
    public FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(request.getCabinClassId())
                .orElseThrow(()-> new Exception("Cabin class not found"));

        SeatMap seatMap=seatMapRepository.findByCabinClassId(cabinClass.getId());

        if(seatMap==null){
            throw new Exception("Seat Map not found");
        }
        if(seatMap.getSeats() == null || seatMap.getSeats().isEmpty()){
            throw new Exception("no seat found in seat map");
        }

        int totalSeats=seatMap.getSeats().size();

        FlightInstanceCabin cabin=FlightInstanceCabin.builder()
                .flightInstanceId(request.getFlightInstanceId())
                .cabinClass(cabinClass)
                .totalSeats(totalSeats)
                .bookedSeats(0)
                .build();

        FlightInstanceCabin savedCabin=flightInstanceCabinRepository.save(cabin);

//      generate seat instances

        List<SeatInstance> seatInstances=seatMap.getSeats().stream()
                .map(
                        seat->{
                            Double premiumSuperCharge=getPremiumSuperCharge(
                                    seat.getSeatType(),
                                    1000.0,
                                    500.0
                            );
                            SeatInstance seatInstance=SeatInstance.builder()
                                    .flightId(request.getFlightId())
                                    .status(SeatAvailabilityStatus.AVAILABLE)
                                    .flightInstanceId(request.getFlightInstanceId())
                                    .flightInstanceCabin(savedCabin)
                                    .seat(seat)
                                    .isAvailable(true)
                                    .isBooked(false)
                                    .premiumSupercharge(premiumSuperCharge)
                                    .build();
                            return seatInstance;
                        }
                ).toList();

        seatInstanceRepository.saveAll(seatInstances);
        savedCabin.setSeats(seatInstances);



        return FlightInstanceCabinMapper.toResponse(savedCabin);
    }

    @Override
    public FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) {
        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Flight instance cabin not found with id: " + id));
        return FlightInstanceCabinMapper.toResponse(fic);
    }

    @Override
    public Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long id, Pageable pageable) {
        return flightInstanceCabinRepository.findByFlightInstanceId(id, pageable)
                .map(FlightInstanceCabinMapper::toResponse);
    }

    @Override
    public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {
        FlightInstanceCabin cabin= flightInstanceCabinRepository.findByFlightInstanceIdAndCabinClassId(
                flightInstanceId,
                cabinClassId
        );
        return FlightInstanceCabinMapper.toResponse(cabin);
    }

    @Override
    public FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request) {
        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Flight instance cabin not found with id: " + id));

        if(request.getCabinClassId()!=null){
            CabinClass cabinClass=cabinClassRepository.findById(request.getCabinClassId())
                    .orElseThrow(() -> new EntityNotFoundException("cabin class not found"));
            fic.setCabinClass(cabinClass);
        }
        FlightInstanceCabin updated=flightInstanceCabinRepository.save(fic);
        return FlightInstanceCabinMapper.toResponse(updated);
    }

    @Override
    public void deleteFlightInstanceCabin(Long id) {

        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Flight instance cabin not found with id: " + id));
        flightInstanceCabinRepository.delete(fic);

    }

    private Double getPremiumSuperCharge(SeatType seatType,
                                         Double windowSuperCharge,
                                         Double aisleSuperCharge){
        if(seatType==null)return 0.0;

        return switch (seatType){
            case AISLE -> aisleSuperCharge;
            case WINDOW -> windowSuperCharge;
            default -> 0.0;
        };
    }
}

