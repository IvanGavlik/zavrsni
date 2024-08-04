package hr.vsite.igavlik.zavrsnirad.dto.v2;

import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureChartDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChartWrapperDto {
    private StatisticalFeatureChartDto homeTeamChart;
    private StatisticalFeatureChartDto guestTeamChart;
    private StatisticalFeatureChartDto chartLeague;
}
