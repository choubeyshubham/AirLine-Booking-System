package in.choubeyshubham.locationservice.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import in.choubeyshubham.embeddable.Address;
import in.choubeyshubham.embeddable.GeoCode;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 3)
    private String iataCode;
    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private GeoCode geoCode;

    @Column(name = "time_zone_id", length = 50)
    private String timeZone;

    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public String getDetailName(){
        if (city != null && city.getCountryCode()!=null){
            return name.toUpperCase()+ "/"+city.getCountryCode();
        }
        return name.toUpperCase();
    }
}
