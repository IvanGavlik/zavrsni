package hr.vsite.igavlik.zavrsnirad.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StatisticalFeatureTableItemDto {
    private Integer goalId;
    private Integer frequency;
    private Double relativeFrequency;
    private Double arithmeticMiddle; // aritmetička sredina
    private Double dispersion; // varijanca
    private Double deviation; // standardna devijacija
}
