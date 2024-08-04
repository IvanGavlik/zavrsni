package hr.vsite.igavlik.zavrsnirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class PoissonStatisticsTeamDto {
    private String teamName;
    private StatisticalFeatureChartDto statisticalFeatureChart;
    private List<StatisticalFeatureTableItemDto> statisticalFeatureTable;
}
