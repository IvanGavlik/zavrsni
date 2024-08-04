package hr.vsite.igavlik.zavrsnirad.service.v3.match;

import hr.vsite.igavlik.zavrsnirad.dto.v3.LeagueReview;
import hr.vsite.igavlik.zavrsnirad.dto.v3.PoissonCard;
import hr.vsite.igavlik.zavrsnirad.dto.v3.PoissonCardDetails;
import hr.vsite.igavlik.zavrsnirad.dto.v3.TeamReview;
import hr.vsite.igavlik.zavrsnirad.service.v3.FootballGameService;
import hr.vsite.igavlik.zavrsnirad.service.v3.formula.FormulaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MatchPredictServiceImpl implements MatchPredictService {

    private static Logger logger = LoggerFactory.getLogger(MatchPredictServiceImpl.class);

    private FootballGameService gameService;
    private FormulaService formulaService;
    private List<Integer> goalIndex;

    @Autowired
    public MatchPredictServiceImpl(FootballGameService gameService, FormulaService formulaService) {
        this.gameService = gameService;
        this.formulaService = formulaService;
        this.goalIndex = new ArrayList<>(5);
        goalIndex.add(0);
        goalIndex.add(1);
        goalIndex.add(2);
        goalIndex.add(3);
        goalIndex.add(4);
        goalIndex.add(5);
    }

    @Override
    public PoissonCard getPoissonCard(String homeTeamName, String guestTeamName) {
        if (Objects.isNull(homeTeamName) || homeTeamName.isEmpty() || Objects.isNull(guestTeamName) || guestTeamName.isEmpty()) {
            logger.warn("Not found team {}, {}" , homeTeamName, guestTeamName);
            return null;
        }

        TeamReview homeTeam = this.gameService.getTeamReviews().stream()
                .filter(teamReview -> teamReview.getName().equals(homeTeamName))
                .findFirst().get();

        TeamReview guestTeam = this.gameService.getTeamReviews().stream()
                .filter(teamReview -> teamReview.getName().equals(guestTeamName))
                .findFirst().get();

        PoissonCard poissonCard = new PoissonCard(homeTeam, guestTeam);
        this.calculatePoisson(poissonCard);
        return poissonCard;
    }

    @Override
    public PoissonCardDetails getPoissonCardDetails(String homeTeamName, String guestTeamName) {
        if (Objects.isNull(homeTeamName) || homeTeamName.isEmpty() || Objects.isNull(guestTeamName) || guestTeamName.isEmpty()) {
            logger.warn("Not found team {}, {}" , homeTeamName, guestTeamName);
            return null;
        }

        TeamReview homeTeam = this.gameService.getTeamReviews().stream()
                .filter(teamReview -> teamReview.getName().equals(homeTeamName))
                .findFirst().get();

        TeamReview guestTeam = this.gameService.getTeamReviews().stream()
                .filter(teamReview -> teamReview.getName().equals(guestTeamName))
                .findFirst().get();


        PoissonCardDetails p = new PoissonCardDetails(homeTeam, guestTeam, this.gameService.getLeagueReview());
        return p;
    }

    /**
     * calculate Poisson
     * za homeTeam LAMBDA = homeTeamAsHomeVrijednostNapada * guestTeamAsGuestVrijednostObrane * ligaDomaceUtakmiceProsjektPostignutig
     * za guestTeam lAMBDA = guestTeamAsGuestVrijjednostNapada * homeTeamAsHomeVrijdnostObrane * ligaGuestUtakmiceProsjekPostihnutih
     *
     */
    private void calculatePoisson(PoissonCard poissonCard) {
        final LeagueReview leagueReview = this.gameService.getLeagueReview();
        double homeTeamLambda = poissonCard.getHomeTeamHomeAttackFactor() * poissonCard.getGuestTeamGuestDefenseFactor() * leagueReview.getHomeAverageGoals();
        poissonCard.setHomeTeamLambda(homeTeamLambda);

        double guestTeamLambda = poissonCard.getGuestTeamGuestAttackFactor() * poissonCard.getHomeTeamHomeDefenseFactor() * leagueReview.getGuestAverageGoals();
        poissonCard.setGuestTeamLambda(guestTeamLambda);

        double guestPoissonMax = -1.0;
        int guestGoalIndex = -1;
        double homePoissonMax = -1.0;
        int homeGoalIndex = -1;

        for (Integer goal : this.goalIndex) {

            double homePoisson = this.formulaService.calculatePoisson(poissonCard.getHomeTeamLambda(), goal);
            this.setHomeTeamProbability(poissonCard, goal, homePoisson);
            logger.warn("homeTeam {} {}", goal, homePoisson);
            if (homePoisson > homePoissonMax) {
                homePoissonMax = homePoisson;
                homeGoalIndex = goal;
            }

            double guestPoisson = this.formulaService.calculatePoisson(poissonCard.getGuestTeamLambda(), goal);
            this.setGuestTeamProbability(poissonCard, goal, guestPoisson);
            logger.warn("guestTeam {} {}", goal, guestPoisson);
            if (guestPoisson > guestPoissonMax) {
                guestPoissonMax = guestPoisson;
                guestGoalIndex = goal;
            }

        }

        poissonCard.setHomeTeamGoalGuess(homeGoalIndex);
        poissonCard.setGuestTeamGoalGuess(guestGoalIndex);
        poissonCard.setProbabilityOfGuess(homePoissonMax * guestPoissonMax);
    }

    void setHomeTeamProbability(PoissonCard poissonCard, int goalIndex, double probability) {
        switch (goalIndex) {
            case 0:
                poissonCard.setHomeTeamProbabilityOf0(probability);
                break;
            case 1:
                poissonCard.setHomeTeamProbabilityOf1(probability);
                break;
            case 2:
                poissonCard.setHomeTeamProbabilityOf2(probability);
                break;
            case 3:
                poissonCard.setHomeTeamProbabilityOf3(probability);
                break;
            case 4:
                poissonCard.setHomeTeamProbabilityOf4(probability);
                break;
            case 5:
                poissonCard.setHomeTeamProbabilityOf5(probability);
                break;
        }
    }

    void setGuestTeamProbability(PoissonCard poissonCard, int goalIndex, double probability) {
        switch (goalIndex) {
            case 0:
                poissonCard.setGuestTeamProbabilityOf0(probability);
                break;
            case 1:
                poissonCard.setGuestTeamProbabilityOf1(probability);
                break;
            case 2:
                poissonCard.setGuestTeamProbabilityOf2(probability);
                break;
            case 3:
                poissonCard.setGuestTeamProbabilityOf3(probability);
                break;
            case 4:
                poissonCard.setGuestTeamProbabilityOf4(probability);
                break;
            case 5:
                poissonCard.setGuestTeamProbabilityOf5(probability);
                break;
        }
    }


}
