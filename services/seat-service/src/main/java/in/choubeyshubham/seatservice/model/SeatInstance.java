package in.choubeyshubham.seatservice.model;


import in.choubeyshubham.enums.SeatAvailabilityStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class SeatInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long flightId;


    @ManyToOne
    private FlightInstanceCabin flightInstanceCabin;

    private Long flightInstanceId;

    @ManyToOne
    private Seat seat;

    private SeatAvailabilityStatus status= SeatAvailabilityStatus.AVAILABLE;

    private boolean isBooked=false;
    private boolean isAvailable=true;

    private Double fare;
    private Double premiumSupercharge;

    private Long flightScheduleId;

    @Version
    private Long version;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}

