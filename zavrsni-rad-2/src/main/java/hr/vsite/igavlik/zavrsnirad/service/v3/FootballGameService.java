package hr.vsite.igavlik.zavrsnirad.service.v3;


import hr.vsite.igavlik.zavrsnirad.dto.v3.*;
import hr.vsite.igavlik.zavrsnirad.entity.FootballGame;

import java.util.List;
import java.util.stream.Stream;

public interface FootballGameService {

    Stream<FootballGame> getFootballGames();

    LeagueReview getLeagueReview();

    List<TeamReview> getTeamReviews();

    List<LeagueReviewBestAttack> findBestAttack();

    List<LeagueReviewBestDefence> findBestDefence();

    LeagueFrequencyChartWrapper leagueFrequencyChart();

    LeagueRelativeFrequencyChartWrapper leagueRelativeFrequencyChart();
}
