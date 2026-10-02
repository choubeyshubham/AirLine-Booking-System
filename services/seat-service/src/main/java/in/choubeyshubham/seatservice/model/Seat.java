package in.choubeyshubham.seatservice.model;

import in.choubeyshubham.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private Integer seatRow;

    private Character columnLetter;

    private SeatType seatType;

    private Double basePrice;

    private Double premiumSuperCharge;

    private Boolean isAvailable=true;

    private Boolean isBlocked=false;

    private Boolean isEmergencyExist=false;

    private Boolean isActive=true;

    private Boolean hasExtraLegroom=false;

    private Boolean hasPowerOutlet=false;

    private Boolean hasTvScreen=false;

    private Boolean hasExtraWidth = false;

    private Integer seatPitch;
    private Integer seatWidth;


    @ManyToOne
    private SeatMap seatMap;

    @ManyToOne
    private CabinClass cabinClass;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Version
    private Long version;

    public Double getTotalPrice(){
        Double total=basePrice!=null?basePrice:0;
        if(premiumSuperCharge!=null){
            total+=premiumSuperCharge;
        }
        return total;
    }

    public boolean isBookable(){
        return isActive && isAvailable && !isBlocked;
    }
    public String getFullPosition(){
        return seatRow + ""+columnLetter;
    }



}