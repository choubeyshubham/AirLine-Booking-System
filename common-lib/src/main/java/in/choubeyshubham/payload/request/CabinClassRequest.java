package in.choubeyshubham.payload.request;


import in.choubeyshubham.enums.CabinClassType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabinClassRequest {

    @NotNull(message = "Name is required")
    private CabinClassType name;

    @NotBlank(message = "code is required")
    private String code;

    private String description;

    @NotNull(message = "aircraftId is required")
    private Long aircraftId;

    private Integer displayOrder;
    private Boolean isActive;
    private Boolean isBookable;
    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;
    private String seatType;
}
