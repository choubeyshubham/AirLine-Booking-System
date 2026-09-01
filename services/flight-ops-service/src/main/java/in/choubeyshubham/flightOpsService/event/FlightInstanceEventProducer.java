package in.choubeyshubham.flightOpsService.event;

import in.choubeyshubham.event.FlightInstanceCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FlightInstanceEventProducer {

    private final KafkaTemplate<String, FlightInstanceCreatedEvent> kafkaTemplate;

    public void sendFlightInstanceCreated(FlightInstanceCreatedEvent event) {
        kafkaTemplate.send("flight-instance-created", event);
    }
}
