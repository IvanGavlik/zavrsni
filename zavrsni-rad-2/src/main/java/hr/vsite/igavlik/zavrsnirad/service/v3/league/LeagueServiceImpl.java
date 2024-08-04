package hr.vsite.igavlik.zavrsnirad.service.v3.league;

import hr.vsite.igavlik.zavrsnirad.dto.v3.*;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.service.v3.FootballGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    private FootballGameService gameService;

    @Autowired
    public LeagueServiceImpl(FootballGameService gameService) {
        this.gameService = gameService;
    }


    @Override
    public LeagueReview leagueReview() {
        return gameService.getLeagueReview();
    }

    @Override
    public List<LeagueReviewBestAttack> reviewBestAttack() {
        return gameService.findBestAttack();
    }

    @Override
    public List<LeagueReviewBestDefence> findBestDefence() {
        return this.gameService.findBestDefence();
    }

    @Override
    public List<TeamReview> teamReview() {
        return this.gameService.getTeamReviews();
    }

    @Override
    public LeagueFrequencyChartWrapper leagueFrequencyChart() {
        return gameService.leagueFrequencyChart();
    }

    @Override
    public LeagueRelativeFrequencyChartWrapper leagueRelativeFrequencyChart() {
        return gameService.leagueRelativeFrequencyChart();
    }

}
