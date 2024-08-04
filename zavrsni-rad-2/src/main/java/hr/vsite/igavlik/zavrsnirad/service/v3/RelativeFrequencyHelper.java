package hr.vsite.igavlik.zavrsnirad.service.v3;

import hr.vsite.igavlik.zavrsnirad.dto.v3.LeagueFrequencyChartWrapper;
import hr.vsite.igavlik.zavrsnirad.dto.v3.LeagueRelativeFrequencyChartWrapper;
import hr.vsite.igavlik.zavrsnirad.dto.v3.RelativeFrequency;


import java.util.List;
import java.util.stream.Collectors;

public class RelativeFrequencyHelper {

    public static LeagueRelativeFrequencyChartWrapper calculateRelativeFrequency(LeagueFrequencyChartWrapper leagueFrequencyChartWrapper) {

        final Integer allSum = leagueFrequencyChartWrapper.getAll()
                .stream()
                .map(el -> el.getFrequency())
                .reduce(new Integer(0), (start, next) -> start + next);

        final Integer homeSum = leagueFrequencyChartWrapper.getHome()
                .stream()
                .map(el -> el.getFrequency())
                .reduce(new Integer(0), (start, next) -> start + next);

        final Integer guestSum = leagueFrequencyChartWrapper.getGuest()
                .stream()
                .map(el -> el.getFrequency())
                .reduce(new Integer(0), (start, next) -> start + next);

        List<RelativeFrequency> allRelativeFrequencies = leagueFrequencyChartWrapper.getAll()
                .stream()
                .map(el -> new RelativeFrequency(el.getGoal(), el.getFrequency()/ (double) allSum))
                .collect(Collectors.toList());

        List<RelativeFrequency> homeRelativeFrequencies = leagueFrequencyChartWrapper.getHome()
                .stream()
                .map(el -> new RelativeFrequency(el.getGoal(), el.getFrequency()/ (double) homeSum))
                .collect(Collectors.toList());

        List<RelativeFrequency> guestRelativeFrequencies = leagueFrequencyChartWrapper.getGuest()
                .stream()
                .map(el -> new RelativeFrequency(el.getGoal(), el.getFrequency()/ (double) guestSum))
                .collect(Collectors.toList());

        LeagueRelativeFrequencyChartWrapper leagueRelativeFrequencyChartWrapper = new LeagueRelativeFrequencyChartWrapper();
        leagueRelativeFrequencyChartWrapper.setAll(allRelativeFrequencies);
        leagueRelativeFrequencyChartWrapper.setHome(homeRelativeFrequencies);
        leagueRelativeFrequencyChartWrapper.setGuest(guestRelativeFrequencies);

        return leagueRelativeFrequencyChartWrapper;
    }

}
