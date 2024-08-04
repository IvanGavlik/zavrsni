package hr.vsite.igavlik.zavrsnirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StatisticalFeatureChartDto {
    String title;
    List<ChartItemDto> data;
}
