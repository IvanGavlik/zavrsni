package hr.vsite.igavlik.zavrsnirad.dto.v3;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PoissonCardDetails {

    private String homeTeamName;
    private int homeTeamHomeNumberOfGames;
    private int homeTeamHomeNumberOfGoals;
    private double homeTeamHomeNumberOfGoalsAverage;
    private int homeTeamHomeNumberOfGoalsReceived;
    private double homeTeamHomeNumberOfGoalsReceivedAverage;

    private int leagueHomeNumberOfGames;
    private int leagueHomeNumberOfGoals;
    private double leagueHomeNumberOfGoalsAverage;
    private int leagueHomeNumberOfGoalsReceived;
    private double leagueHomeNumberOfGoalsReceivedAverage;

    private String guestTeamName;
    private int guestTeamGuestNumberOfGames;
    private int guestTeamGuestNumberOfGoals;
    private double guestTeamGuestNumberOfGoalsAverage;
    private int guestTeamGuestNumberOfGoalsReceived;
    private double guestTeamGuestNumberOfGoalsReceivedAverage;

    private int leagueGuestNumberOfGames;
    private int leagueGuestNumberOfGoals;
    private double leagueGuestNumberOfGoalsAverage;
    private int leagueGuestNumberOfGoalsReceived;
    private double leagueGuestNumberOfGoalsReceivedAverage;

    public PoissonCardDetails(TeamReview homeTeam, TeamReview guestTeam, LeagueReview leagueReview) {
        this.homeTeamName = homeTeam.getName();
        this.homeTeamHomeNumberOfGames = homeTeam.getHomeNumberOFGames();
        this.homeTeamHomeNumberOfGoals = homeTeam.getHomeNumberOFGoals();
        this.homeTeamHomeNumberOfGoalsAverage = homeTeam.getHomeAverageGoals();
        this.homeTeamHomeNumberOfGoalsReceived = homeTeam.getReceivedHomeNumberOFGoals();
        this.homeTeamHomeNumberOfGoalsReceivedAverage = homeTeam.getReceivedHomeAverageGoals();

        this.leagueHomeNumberOfGames = leagueReview.getHomeNumberOFGames();
        this.leagueHomeNumberOfGoals = leagueReview.getHomeNumberOFGoals();
        this.leagueHomeNumberOfGoalsAverage = leagueReview.getHomeAverageGoals();
        this.leagueHomeNumberOfGoalsReceived = leagueReview.getGuestNumberOFGoals(); // TODO CHECK
        this.leagueHomeNumberOfGoalsReceivedAverage = leagueReview.getGuestAverageGoals(); // TODO CHECK

        this.guestTeamName = guestTeam.getName();
        this.guestTeamGuestNumberOfGames = guestTeam.getGuestNumberOFGames();
        this.guestTeamGuestNumberOfGoals = guestTeam.getGuestNumberOFGoals();
        this.guestTeamGuestNumberOfGoalsAverage = guestTeam.getGuestAverageGoals();
        this.guestTeamGuestNumberOfGoalsReceived = guestTeam.getReceivedGuestNumberOFGoals();
        this.guestTeamGuestNumberOfGoalsReceivedAverage = guestTeam.getReceivedGuestAverageGoals();

        this.leagueGuestNumberOfGames = leagueReview.getGuestNumberOFGames();
        this.leagueGuestNumberOfGoals = leagueReview.getGuestNumberOFGoals();
        this.leagueGuestNumberOfGoalsAverage = leagueReview.getGuestAverageGoals();
        this.leagueGuestNumberOfGoalsReceived = leagueReview.getHomeNumberOFGoals(); // TODO CHECK
        this.leagueGuestNumberOfGoalsReceivedAverage = leagueReview.getHomeAverageGoals(); // TODO CHECK
    }
}
