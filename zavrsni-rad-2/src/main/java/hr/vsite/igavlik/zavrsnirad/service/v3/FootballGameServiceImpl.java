package hr.vsite.igavlik.zavrsnirad.service.v3;

import hr.vsite.igavlik.zavrsnirad.dto.v3.*;
import hr.vsite.igavlik.zavrsnirad.entity.FootballGame;
import hr.vsite.igavlik.zavrsnirad.repository.FootballGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FootballGameServiceImpl implements FootballGameService {

    private List<FootballGame> footballGameList;

    private LeagueReview leagueReview;

    private List<TeamReview> teamReviews;

    private FootballGameRepository repository;

    private LeagueFrequencyChartWrapper leagueFrequencyChartWrapper;

    private LeagueRelativeFrequencyChartWrapper leagueRelativeFrequencyChartWrapper;

    @Autowired
    public FootballGameServiceImpl(FootballGameRepository repository) {
        this.repository = repository;
        this.footballGameList = repository.findAll();
        this.leagueReview = LeagueReviewHelper.calculateLeagueReview(this.footballGameList);
        this.teamReviews = TeamReviewHelper.calculateTeamReviews(this.leagueReview, this.footballGameList);

        leagueFrequencyChartWrapper = new LeagueFrequencyChartWrapper();
        leagueFrequencyChartWrapper.setAll(this.leagueFrequencyChartAll());
        leagueFrequencyChartWrapper.setHome(this.leagueFrequencyChartHome());
        leagueFrequencyChartWrapper.setGuest(this.leagueFrequencyChartGuest());

        this.leagueRelativeFrequencyChartWrapper = RelativeFrequencyHelper.calculateRelativeFrequency(this.leagueFrequencyChartWrapper);
    }

    @Override
    public Stream<FootballGame> getFootballGames() {
        return this.footballGameList.stream();
    }

    @Override
    public LeagueReview getLeagueReview() { return this.leagueReview; }

    @Override
    public List<TeamReview> getTeamReviews() {  return this.teamReviews;  }

    @Override
    public List<LeagueReviewBestAttack> findBestAttack() {
        List<LeagueReviewBestAttack> bestAttack = this.teamReviews.stream()
                .sorted((t1, t2) -> (int) ((t2.getAllAttackFactor() * 100000 ) - (t1.getAllAttackFactor() * 100000) ))
                .map( el -> {
                    return new LeagueReviewBestAttack() {
                        @Override
                        public String getTeamName() {
                            return el.getName();
                        }

                        @Override
                        public double getNumberOfGoalsPerGame() {
                            return el.getAllAttackFactor();
                        }

                        @Override
                        public int getNumberOfGoals() {
                            return el.getAllNumberOFGoals();
                        }
                    };
                } )
                .limit(3)
                .collect(Collectors.toList());
        ;
        return bestAttack;
    }


    @Override
    public List<LeagueReviewBestDefence> findBestDefence() {
        // TODO MANJE JE BOLJE TAKO JE TRENUTNO
        List<LeagueReviewBestDefence> bestDefence = this.teamReviews.stream()
                .sorted((t1, t2) -> (int) ((t1.getAllDefensekFactor() * 100000 ) - (t2.getAllDefensekFactor() * 100000) ))
                .map(el -> {
                    return new LeagueReviewBestDefence() {
                        @Override
                        public String getTeamName() {
                            return el.getName();
                        }

                        @Override
                        public double getNumberOfGoalsPerGame() {
                            return el.getAllDefensekFactor();
                        }

                        @Override
                        public int getNumberOfGoals() {
                            return el.getReceivedAllNumberOFGoals();
                        }
                    };
                })
                .limit(3)
                .collect(Collectors.toList());
        ;
        return bestDefence;

    }

    @Override
    public LeagueFrequencyChartWrapper leagueFrequencyChart() {
        return this.leagueFrequencyChartWrapper;
    }

    @Override
    public LeagueRelativeFrequencyChartWrapper leagueRelativeFrequencyChart() {
        return this.leagueRelativeFrequencyChartWrapper;
    }

    private List<LeagueFrequencyChart> leagueFrequencyChartAll() {
        return this.repository.leagueFrequencyChartAll();
    }

    private List<LeagueFrequencyChart> leagueFrequencyChartHome() {
        return this.repository.leagueFrequencyChartHome();
    }

    private List<LeagueFrequencyChart> leagueFrequencyChartGuest() { return this.repository.leagueFrequencyChartGuest();  }

}
