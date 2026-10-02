package in.choubeyshubham.seatservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class SeatMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int totalRows;

    @Column(nullable = false)
    private int rightSeatsPerRow;

    @Column(nullable = false)
    private int leftSeatsPerRow;

    @Column(nullable = false)
    private Long airlineId;


    @OneToMany(mappedBy = "seatMap", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    @OneToOne
    private CabinClass cabinClass;
}
