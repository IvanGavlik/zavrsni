package hr.vsite.igavlik.zavrsnirad.dto.v3;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LeagueRelativeFrequencyChartWrapper {
    private List<RelativeFrequency> all;
    private List<RelativeFrequency> home;
    private List<RelativeFrequency> guest;
}
