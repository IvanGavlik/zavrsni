package hr.vsite.igavlik.zavrsnirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PoissonMatrixDto {
    private String homeTeamName;
    private String guestTeamName;
    private Matrix5x5Dto homeGuestTeamsPredictionMatrix;
}
