package in.choubeyshubham.payload.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatMapRequest {

    @NotBlank(message = "Seat map name is required")
    private String name;

    @Positive
    @NotNull(message = "total row is required")
    private Integer totalRows;

    @NotNull(message = "Left seats per row is required")
    @Positive
    private Integer leftSeatsPerRow;

    @NotNull(message = "Right seats per row is required")
    @Positive
    private Integer rightSeatsPerRow;

    private Long cabinClassId;
}

