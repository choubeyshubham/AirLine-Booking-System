package in.choubeyshubham.ancillaryservice.model;


import in.choubeyshubham.ancillaryservice.services.AncillaryMetadataConverter;
import in.choubeyshubham.domain.AncillaryMetadata;
import in.choubeyshubham.enums.AncillaryType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ancillary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AncillaryType type;

    private String subType;

    private String rfisc;

    @Column(nullable = false)
    private String name;

    private String description;

    @Convert(converter= AncillaryMetadataConverter.class)
    private AncillaryMetadata metadata;

    private Integer displayOrder;

    private Long airlineId;


}
