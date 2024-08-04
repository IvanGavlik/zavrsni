package hr.vsite.igavlik.zavrsnirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PoissonDto {
    PoissonStatisticsTeamDto homeTeam;
    PoissonStatisticsTeamDto guestTeam;
    PoissonMatrixDto poissonMatrix;
}
