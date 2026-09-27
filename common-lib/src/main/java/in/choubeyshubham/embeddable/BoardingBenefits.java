package in.choubeyshubham.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardingBenefits {

    @Column(name = "priority_boarding", nullable = false)
    @Builder.Default
    private Boolean priorityBoarding = false;

    @Column(name = "priority_checkin", nullable = false)
    @Builder.Default
    private Boolean priorityCheckin = false;

    @Column(name = "fast_track_security", nullable = false)
    @Builder.Default
    private Boolean fastTrackSecurity = false;
}
