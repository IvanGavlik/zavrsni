package hr.vsite.igavlik.zavrsnirad.service.v3;

import hr.vsite.igavlik.zavrsnirad.dto.v3.LeagueReview;
import hr.vsite.igavlik.zavrsnirad.entity.FootballGame;

import java.util.List;

public final class LeagueReviewHelper {

    public static LeagueReview calculateLeagueReview(List<FootballGame> footballGames) {
        LeagueReview leagueReview = new LeagueReview();

        int numberOfGames = (int) footballGames.stream().count();
        leagueReview.setAllNumberOFGames(numberOfGames);
        leagueReview.setHomeNumberOFGames(numberOfGames);
        leagueReview.setGuestNumberOFGames(numberOfGames);

        int allScore = 0;
        int homeScore = 0;
        int guestScore = 0;
        for (int i = 0; i < footballGames.size(); i++) {
            FootballGame footballGame = footballGames.get(i);

             homeScore += footballGame.getHomeTeamScore();
             guestScore += footballGame.getGuestTeamScore();
             allScore = (footballGame.getHomeTeamScore() + footballGame.getGuestTeamScore()) + allScore;
        }

        leagueReview.setAllNumberOFGoals(allScore);
        leagueReview.setAllAverageGoals(allScore / (double) numberOfGames);

        leagueReview.setHomeNumberOFGoals(homeScore);
        leagueReview.setHomeAverageGoals(homeScore / (double) numberOfGames );

        leagueReview.setGuestNumberOFGoals(guestScore);
        leagueReview.setGuestAverageGoals(guestScore / (double) numberOfGames );

        return leagueReview;
    }
}
