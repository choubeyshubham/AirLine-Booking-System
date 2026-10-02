package in.choubeyshubham.seatservice.model;


import in.choubeyshubham.enums.CabinClassType;
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
public class CabinClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CabinClassType name;

    @Column(nullable = false)
    private String code;

    private String description;


    @OneToOne(mappedBy = "cabinClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private SeatMap seatMap;

    @Column(nullable = false)
    private Long aircraftId;

    @Column(nullable = false)
    private Integer displayOrder=0;

    @Column(nullable = false)
    private Boolean isActive=true;

    @Column(nullable = false)
    private Boolean isBookable = true;

    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;
    private String seatType;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;
}
