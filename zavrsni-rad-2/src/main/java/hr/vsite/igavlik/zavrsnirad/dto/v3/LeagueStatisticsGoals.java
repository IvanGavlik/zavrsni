package hr.vsite.igavlik.zavrsnirad.dto.v3;

public interface LeagueStatisticsGoals {
    String getTeam();
    int getPlayedMatch();
    int getGoalsScored();
    double getGoalsScoredAverage();
    int getgoalsConceded();
    double getGoalsConcededAverage();
}
