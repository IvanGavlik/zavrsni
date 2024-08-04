package hr.vsite.igavlik.zavrsnirad.dto.v3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class LeagueFrequencyChartWrapper {
    private List<LeagueFrequencyChart> all;
    private List<LeagueFrequencyChart> home;
    private List<LeagueFrequencyChart> guest;
}
