package hr.vsite.igavlik.zavrsnirad.repository;

import hr.vsite.igavlik.zavrsnirad.dto.v3.*;
import hr.vsite.igavlik.zavrsnirad.entity.FootballGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FootballGameRepository extends PagingAndSortingRepository<FootballGame, UUID> {

    List<FootballGame> findAll();

    String bestAttack = "select s2.home_team teamName, sum(suma) numberOfGoals, sum(suma)/sum(s2.kol) numberOfGoalsPerGame  from\n" +
            "( select s.home_team, count(s) kol, sum(s.home_team_goals) suma from public.skotska1_2018_2019 s\n" +
            "group by s.home_team \n" +
            "union \n" +
            "select s.guest_team , count(s) kol, sum(s.guest_team_goals) suma from public.skotska1_2018_2019 s\n" +
            "group by s.guest_team ) s2\n" +
            "group by s2.home_team \n" +
            "order by numberOfGoalsPerGame desc\n" +
            "limit 3\n" +
            ";";
    @Query(value = bestAttack, nativeQuery = true)
    List<LeagueReviewBestAttack> findBestAttack();

    String goalSum = "select sum(s.home_team_goals) homeSum, sum(s.guest_team_goals) guestSum from public.skotska1_2018_2019 s";
    @Query(value = goalSum, nativeQuery = true)
    List<GoalSum> goalSum();

    String bestDefence = "select s2.home_team teamName, sum(suma) numberOfGoals, sum(suma)/sum(s2.kol) numberOfGoalsPerGame  from\n" +
            "( select s.home_team, count(s) kol, sum(s.guest_team_goals) suma from public.skotska1_2018_2019 s\n" +
            "group by s.home_team \n" +
            "union \n" +
            "select s.guest_team , count(s) kol, sum(s.home_team_goals) suma from public.skotska1_2018_2019 s\n" +
            "group by s.guest_team ) s2\n" +
            "group by s2.home_team \n" +
            "order by numberOfGoalsPerGame asc\n" +
            "limit 3;\n";
    @Query(value = bestDefence, nativeQuery = true)
    List<LeagueReviewBestDefence> findBestDefence();

    String leagueByGoalsAll = "select s2.home_team team, sum(kol) playedMatch, sum(postignuti) goalsScored, sum(postignuti)/sum(kol) goalsScoredAverage, sum(primljeni) goalsConceded, sum(primljeni)/sum(kol) goalsConcededAverage from\n" +
            "( select s.home_team, count(s) kol, sum(s.home_team_goals) postignuti, sum(s.guest_team_goals) primljeni  from public.skotska1_2018_2019 s\n" +
            "group by s.home_team \n" +
            "union \n" +
            "select s.guest_team , count(s) kol, sum(s.guest_team_goals) postignuti, sum(s.home_team_goals) primljeni from public.skotska1_2018_2019 s\n" +
            "group by s.guest_team ) s2\n" +
            "group by s2.home_team \n" +
            "order by goalsScoredAverage desc;";
    @Query(value = leagueByGoalsAll, nativeQuery = true)
    List<LeagueStatisticsGoals> leagueByGoalsAll();

    String leagueByGoalsHome = "select s.home_team team, count(s) playedMatch, sum(s.home_team_goals) goalsScored,  sum(s.home_team_goals)/ cast (count(s) as DOUBLE PRECISION)  goalsScoredAverage, sum(s.guest_team_goals) goalsConceded, sum(s.guest_team_goals )/ cast (count(s) as DOUBLE PRECISION) goalsConcededAverage from public.skotska1_2018_2019 s\n" +
            "group by s.home_team order by goalsScoredAverage desc;";
    @Query(value = leagueByGoalsHome, nativeQuery = true)
    List<LeagueStatisticsGoals> leagueByGoalsHome();

    String leagueByGoalsGuest = "select s.guest_team team, count(s) playedMatch, sum(s.guest_team_goals) goalsScored,  sum(s.guest_team_goals)/ cast (count(s) as DOUBLE PRECISION) goalsScoredAverage,  sum(s.home_team_goals) goalsConceded, sum(s.home_team_goals )/ cast (count(s) as DOUBLE PRECISION) goalsConcededAverage from public.skotska1_2018_2019 s\n" +
            "group by s.guest_team\n" +
            "order by goalsScoredAverage desc";
    @Query(value = leagueByGoalsGuest, nativeQuery = true)
    List<LeagueStatisticsGoals> leagueByGoalsGuest();

    String LeagueFrequencyChartAll = "select goal, sum(kol) frequency from\n" +
            "( select s.home_team_goals goal, count(*) kol from public.skotska1_2018_2019 s\n" +
            "group by s.home_team_goals \n" +
            "union \n" +
            "select s.guest_team_goals goal, count(*) kol from public.skotska1_2018_2019 s\n" +
            "group by s.guest_team_goals ) s2\n" +
            "group by goal\n" +
            "order by goal asc;";
    @Query(value = LeagueFrequencyChartAll, nativeQuery = true)
    List<LeagueFrequencyChart> leagueFrequencyChartAll();

    String LeagueFrequencyChartHome = "select s.home_team_goals goal, count(*) frequency from public.skotska1_2018_2019 s\n" +
            "group by s.home_team_goals order by goal asc;";
    @Query(value = LeagueFrequencyChartHome, nativeQuery = true)
    List<LeagueFrequencyChart> leagueFrequencyChartHome();

    String LeagueFrequencyChartGuest = "select s.guest_team_goals goal, count(*) frequency from public.skotska1_2018_2019 s\n" +
            "group by s.guest_team_goals order by goal asc;";
    @Query(value = LeagueFrequencyChartGuest, nativeQuery = true)
    List<LeagueFrequencyChart> leagueFrequencyChartGuest();

}
