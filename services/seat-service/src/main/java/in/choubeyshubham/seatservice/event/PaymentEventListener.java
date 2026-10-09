package in.choubeyshubham.seatservice.event;


import in.choubeyshubham.enums.SeatAvailabilityStatus;
import in.choubeyshubham.payload.response.BookingResponse;
import in.choubeyshubham.payload.response.SeatInstanceResponse;
import in.choubeyshubham.seatservice.client.BookingClient;
import in.choubeyshubham.seatservice.service.SeatInstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentEventListener {


    private final BookingClient bookingClient;
    private final SeatInstanceService seatInstanceService;

    @KafkaListener(topics = "payment.completed",groupId = "seat-service-group")
    public void handleBookingConfirmed(PaymentCompletedEvent event){

        BookingResponse bookingResponse=bookingClient.getBookingById(event.getBookingId());

        List<SeatInstanceResponse> seatInstances=bookingResponse.getSeatInstances();

        for(SeatInstanceResponse seatInstanceResponse:seatInstances){
            seatInstanceService.updateSeatInstanceStatus(
                    seatInstanceResponse.getId(),
                    SeatAvailabilityStatus.BOOKED
            );
        }

    }


}
