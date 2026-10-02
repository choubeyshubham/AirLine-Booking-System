package in.choubeyshubham.seatservice.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FlightInstanceCabin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long flightInstanceId;

    @ManyToOne
    private CabinClass cabinClass;

    @Column(nullable = false)
    private Integer totalSeats;

    private Integer bookedSeats=0;

    @OneToMany(mappedBy = "flightInstanceCabin",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatInstance> seats=new ArrayList<>();

    public Integer getAvailableSeats() {
        return totalSeats-bookedSeats;
    }
}
