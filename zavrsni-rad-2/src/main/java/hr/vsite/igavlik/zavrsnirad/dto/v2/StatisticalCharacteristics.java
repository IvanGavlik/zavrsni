package hr.vsite.igavlik.zavrsnirad.dto.v2;

import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureChartDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StatisticalCharacteristics {
    private ChartWrapperDto chart;
    private List<TableItemWrapperDto> tableItems;
}
