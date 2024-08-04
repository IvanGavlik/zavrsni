package hr.vsite.igavlik.zavrsnirad.service.v3;

import hr.vsite.igavlik.zavrsnirad.dto.v3.LeagueReview;
import hr.vsite.igavlik.zavrsnirad.dto.v3.TeamReview;
import hr.vsite.igavlik.zavrsnirad.entity.FootballGame;

import java.util.ArrayList;
import java.util.List;

public class TeamReviewHelper {

    public static List<TeamReview> calculateTeamReviews(LeagueReview leagueReview, List<FootballGame> footballGames) {
        final List<TeamReview> teamReviews = new ArrayList<>(12);
        teamReviews.addAll(addTeams());

        for (int i = 0; i < footballGames.size() -1; i++) {
            FootballGame fg = footballGames.get(i);

            TeamReview homeTeam = teamReviews.stream()
                    .filter(el -> el.getName().equals(fg.getHomeTeam()))
                    .findFirst()
                    .get();

            TeamReview guestTeam = teamReviews.stream()
                    .filter(el -> el.getName().equals(fg.getGuestTeam()))
                    .findFirst()
                    .get();


            homeTeam.setHomeNumberOFGames( homeTeam.getHomeNumberOFGames() + 1);
            homeTeam.setHomeNumberOFGoals( homeTeam.getHomeNumberOFGoals() + fg.getHomeTeamScore() );
            homeTeam.setHomeAverageGoals( homeTeam.getHomeNumberOFGoals() / (double) homeTeam.getHomeNumberOFGames() );

            homeTeam.setAllNumberOFGames( homeTeam.getAllNumberOFGames() + 1 );
            homeTeam.setAllNumberOFGoals(homeTeam.getHomeNumberOFGoals() + homeTeam.getGuestNumberOFGoals() );
            homeTeam.setAllAverageGoals( homeTeam.getAllNumberOFGoals() / (double) homeTeam.getAllNumberOFGames() );

            homeTeam.setReceivedHomeNumberOFGames( homeTeam.getReceivedHomeNumberOFGames() + 1 );
            homeTeam.setReceivedHomeNumberOFGoals( homeTeam.getReceivedHomeNumberOFGoals() + fg.getGuestTeamScore() );
            homeTeam.setReceivedHomeAverageGoals( homeTeam.getReceivedHomeNumberOFGoals() / (double) homeTeam.getReceivedHomeNumberOFGames() );

            homeTeam.setReceivedAllNumberOFGames( homeTeam.getReceivedAllNumberOFGames() + 1 );
            homeTeam.setReceivedAllNumberOFGoals(homeTeam.getReceivedHomeNumberOFGoals() + homeTeam.getReceivedGuestNumberOFGoals() );
            homeTeam.setReceivedAllAverageGoals( homeTeam.getReceivedAllNumberOFGoals() / (double) homeTeam.getReceivedAllNumberOFGames() );


            guestTeam.setGuestNumberOFGames(guestTeam.getGuestNumberOFGames() + 1);
            guestTeam.setGuestNumberOFGoals( guestTeam.getGuestNumberOFGoals() + fg.getGuestTeamScore() );
            guestTeam.setGuestAverageGoals( guestTeam.getGuestNumberOFGoals() / (double) guestTeam.getGuestNumberOFGames() );

            guestTeam.setAllNumberOFGames(guestTeam.getAllNumberOFGames() + 1);
            guestTeam.setAllNumberOFGoals(guestTeam.getHomeNumberOFGoals() + guestTeam.getGuestNumberOFGoals() );
            guestTeam.setAllAverageGoals( guestTeam.getAllNumberOFGoals() / (double) guestTeam.getAllNumberOFGames() );

            guestTeam.setReceivedGuestNumberOFGames( guestTeam.getReceivedGuestNumberOFGames() + 1 );
            guestTeam.setReceivedGuestNumberOFGoals( guestTeam.getReceivedGuestNumberOFGoals() + fg.getHomeTeamScore() );
            guestTeam.setReceivedGuestAverageGoals( guestTeam.getReceivedGuestNumberOFGoals() / (double) guestTeam.getReceivedGuestNumberOFGames() );

            guestTeam.setReceivedAllNumberOFGames(guestTeam.getReceivedAllNumberOFGames() + 1);
            guestTeam.setReceivedAllNumberOFGoals(guestTeam.getReceivedHomeNumberOFGoals() + guestTeam.getReceivedGuestNumberOFGoals() );
            guestTeam.setReceivedAllAverageGoals( guestTeam.getReceivedAllNumberOFGoals() / (double) guestTeam.getReceivedAllNumberOFGames() );


            homeTeam.setAsHomeAttackFactor( homeTeam.getHomeAverageGoals() / leagueReview.getHomeAverageGoals()  );
            homeTeam.setAsHomeDefensekFactor( homeTeam.getReceivedHomeAverageGoals() / leagueReview.getGuestAverageGoals() );
            homeTeam.setAsGuestAttackFactor( homeTeam.getGuestAverageGoals() / leagueReview.getGuestAverageGoals()  );
            homeTeam.setAsGuestDefensekFactor( homeTeam.getReceivedGuestAverageGoals() / leagueReview.getHomeAverageGoals()  );
            homeTeam.setAllAttackFactor( homeTeam.getAsHomeAttackFactor() + homeTeam.getAsGuestAttackFactor() );
            homeTeam.setAllDefensekFactor( homeTeam.getAsHomeDefensekFactor() + homeTeam.getAsGuestDefensekFactor() );

            guestTeam.setAsHomeAttackFactor( guestTeam.getHomeAverageGoals() / leagueReview.getHomeAverageGoals() );
            guestTeam.setAsHomeDefensekFactor( guestTeam.getReceivedHomeAverageGoals() / leagueReview.getGuestAverageGoals() );
            guestTeam.setAsGuestAttackFactor( guestTeam.getGuestAverageGoals() / leagueReview.getGuestAverageGoals()  );
            guestTeam.setAsGuestDefensekFactor( guestTeam.getReceivedGuestAverageGoals() / leagueReview.getHomeAverageGoals() );
            guestTeam.setAllAttackFactor( guestTeam.getAsHomeAttackFactor() + guestTeam.getAsGuestAttackFactor() );
            guestTeam.setAllDefensekFactor( guestTeam.getAsHomeDefensekFactor() + guestTeam.getAsGuestDefensekFactor() );

        }

        return teamReviews;
    }

    private final static String RANGERS = "Rangers FC";
    private final static String CELTIC = "Celtic FC";
    private final static String ABERDEEN = "Aberdeen FC";
    private final static String HIBERNIAN = "Hibernian FC";
    private final static String KILMARNOCK = "Kilmarnock FC";
    private final static String MOTHERWELL = "Motherwell FC";
    private final static String HEART = "Heart of Midlothian FC";
    private final static String LIVINGSTON = "Livingston FC";
    private final static String ST = "St Johnstone FC";
    private final static String SAINT = "Saint Mirren FC";
    private final static String DUNDEE = "Dundee FC";
    private final static String HAMILTON = "Hamilton Academical FC";

    private static List<TeamReview> addTeams() {
        List<TeamReview> teamReviews = new ArrayList<>(10);
        TeamReview t1 = new TeamReview();
        t1.setName(RANGERS);
        teamReviews.add(t1);

        TeamReview t2 = new TeamReview();
        t2.setName(CELTIC);
        teamReviews.add(t2);

        TeamReview t3 = new TeamReview();
        t3.setName(ABERDEEN);
        teamReviews.add(t3);

        TeamReview t4 = new TeamReview();
        t4.setName(HIBERNIAN);
        teamReviews.add(t4);

        TeamReview t5 = new TeamReview();
        t5.setName(KILMARNOCK);
        teamReviews.add(t5);

        TeamReview t6 = new TeamReview();
        t6.setName(MOTHERWELL);
        teamReviews.add(t6);

        TeamReview t7 = new TeamReview();
        t7.setName(HEART);
        teamReviews.add(t7);

        TeamReview t8 = new TeamReview();
        t8.setName(LIVINGSTON);
        teamReviews.add(t8);

        TeamReview t9 = new TeamReview();
        t9.setName(ST);
        teamReviews.add(t9);

        TeamReview t10 = new TeamReview();
        t10.setName(SAINT);
        teamReviews.add(t10);

        TeamReview t11 = new TeamReview();
        t11.setName(DUNDEE);
        teamReviews.add(t11);

        TeamReview t12 = new TeamReview();
        t12.setName(HAMILTON);
        teamReviews.add(t12);

        return teamReviews;
    }
}
