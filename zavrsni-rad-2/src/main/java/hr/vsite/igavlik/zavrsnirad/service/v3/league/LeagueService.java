package hr.vsite.igavlik.zavrsnirad.service.v3.league;

import hr.vsite.igavlik.zavrsnirad.dto.v3.*;

import java.util.List;

public interface LeagueService {

    LeagueReview leagueReview();

    List<LeagueReviewBestAttack> reviewBestAttack();

    List<LeagueReviewBestDefence> findBestDefence();

    List<TeamReview> teamReview();

    LeagueFrequencyChartWrapper leagueFrequencyChart();

    LeagueRelativeFrequencyChartWrapper leagueRelativeFrequencyChart();
}
