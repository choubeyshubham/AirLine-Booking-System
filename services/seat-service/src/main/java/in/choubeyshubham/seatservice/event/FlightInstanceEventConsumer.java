package in.choubeyshubham.seatservice.event;

import in.choubeyshubham.enums.SeatAvailabilityStatus;
import in.choubeyshubham.enums.SeatType;
import in.choubeyshubham.seatservice.model.CabinClass;
import in.choubeyshubham.seatservice.model.FlightInstanceCabin;
import in.choubeyshubham.seatservice.model.Seat;
import in.choubeyshubham.seatservice.model.SeatInstance;
import in.choubeyshubham.seatservice.repository.CabinClassRepository;
import in.choubeyshubham.seatservice.repository.FlightInstanceCabinRepository;
import in.choubeyshubham.seatservice.repository.SeatInstanceRepository;
import in.choubeyshubham.seatservice.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightInstanceEventConsumer {


    private final CabinClassRepository cabinClassRepository;
    private final SeatRepository seatRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;
    private final SeatInstanceRepository seatInstanceRepository;

    @KafkaListener(topics = "flight-instance-created",groupId = "seat-service-group")
    @Transactional
    public void handleFlightInstanceCreated(FlightInstanceCreatedEvent event){

        System.out.println(
                "Flight instance created"+
                        event.getFlightId()+"-"+
                        event.getFlightInstanceId()+"-"+
                        event.getAircraftId()
        );

        List<CabinClass> cabinClasses=cabinClassRepository.findByAircraftId(event.getAircraftId());

        System.out.println(" --------------> "+cabinClasses.size());

        int totalSeatInstances=0;

        for(CabinClass cabinClass: cabinClasses){
            List<Seat> seats=cabinClass.getSeatMap()!=null
                    ? seatRepository.findBySeatMapId(cabinClass.getSeatMap().getId())
                    : List.of();

            FlightInstanceCabin fic=FlightInstanceCabin.builder()
                    .flightInstanceId(event.getFlightInstanceId())
                    .cabinClass(cabinClass)
                    .totalSeats(seats.size())
                    .bookedSeats(0)
                    .build();

            FlightInstanceCabin savedFic=flightInstanceCabinRepository.save(fic);

            List<SeatInstance> seatInstances=seats.stream().map(
                    seat->SeatInstance
                            .builder()
                            .flightId(event.getFlightId())
                            .flightInstanceId(event.getFlightInstanceId())
                            .flightInstanceCabin(savedFic)
                            .seat(seat)
                            .status(SeatAvailabilityStatus.AVAILABLE)
                            .isBooked(false)
                            .isAvailable(true)
                            .premiumSupercharge(getPremiumSuperCharge(seat.getSeatType(),
                                    1000.0,500.0))

                            .build()
            ).toList();

            seatInstanceRepository.saveAll(seatInstances);
            totalSeatInstances+=seatInstances.size();
        }
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
