package hr.vsite.igavlik.zavrsnirad.dto.v2;

import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureChartDto;
import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureTableItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TableItemWrapperDto {
    private StatisticalFeatureTableItemDto homeTeam;
    private StatisticalFeatureTableItemDto guestTeam;
    private StatisticalFeatureTableItemDto league;
}
