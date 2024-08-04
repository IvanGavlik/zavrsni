package hr.vsite.igavlik.zavrsnirad.dto.v3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoissonCard {
    private String homeTeamName;
    private int homeTeamGoalGuess;
    private double homeTeamLambda;
    private double homeTeamHomeAttackFactor;
    private double  homeTeamHomeDefenseFactor;

    private String guestTeamName;
    private int  guestTeamGoalGuess;
    private double guestTeamLambda;
    private double guestTeamGuestAttackFactor;
    private double guestTeamGuestDefenseFactor;

    private double probabilityOfGuess;

    private double homeTeamProbabilityOf0;
    private double homeTeamProbabilityOf1;
    private double homeTeamProbabilityOf2;
    private double homeTeamProbabilityOf3;
    private double homeTeamProbabilityOf4;
    private double homeTeamProbabilityOf5;

    private double guestTeamProbabilityOf0;
    private double guestTeamProbabilityOf1;
    private double guestTeamProbabilityOf2;
    private double guestTeamProbabilityOf3;
    private double guestTeamProbabilityOf4;
    private double guestTeamProbabilityOf5;

    public PoissonCard(TeamReview homeTeam, TeamReview guestTeam) {
        this.homeTeamName = homeTeam.getName();
        this.homeTeamHomeAttackFactor = homeTeam.getAsHomeAttackFactor();
        this.homeTeamHomeDefenseFactor = homeTeam.getAsHomeDefensekFactor();

        this.guestTeamName = guestTeam.getName();
        this.guestTeamGuestAttackFactor = guestTeam.getAsGuestAttackFactor();
        this.guestTeamGuestDefenseFactor = guestTeam.getAsGuestDefensekFactor();
    }
}
