package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.entity.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class DefaultScoreService {

    private static Logger logger = LoggerFactory.getLogger(DefaultScoreService.class);
    private static final int SUM_ZERO = 0;


    public int calculateScoreSum(List<Event> teamEvents) {
        logger.info("calculateScoreSum {} {}", teamEvents);
        int sum = teamEvents.stream()
                .map((Event event) -> event.getHomeTeamScore() + event.getGuestTeamScore())
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateScoreSum(List<Event> teamEvents, final String teamName) {
        logger.info("calculateScoreSum {} {}", teamName, teamEvents);
        Function<Event, Integer> mapScoreEvent = (Event event) -> {
            if(event.getHomeTeam().equals(teamName)) {
                return event.getHomeTeamScore();
            }
            if(event.getGuestTeam().equals(teamName)) {
                return event.getGuestTeamScore();
            }
            return 0;
        };
        int sum = teamEvents.stream()
                .map(mapScoreEvent)
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateHomeScoreSum(List<Event> teamEvents) {
        logger.info("calculateHomeScoreSum {} {}", teamEvents);
        int sum = teamEvents.stream()
                .map((Event event) -> event.getHomeTeamScore())
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateHomeScoreSum(List<Event> teamEvents, final String teamName) {
        logger.info("calculateHomeScoreSum {} {}", teamName, teamEvents);
        Function<Event, Integer> mapScoreEvent = (Event event) -> {
            if(event.getHomeTeam().equals(teamName)) {
                return event.getHomeTeamScore();
            }
            return 0;
        };

        int sum = teamEvents.stream()
                .map(mapScoreEvent)
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateGuestScoreSum(List<Event> teamEvents) {
        logger.info("calculateGuestScoreSum {} {}", teamEvents);
        int sum = teamEvents.stream()
                .map((Event event) -> event.getGuestTeamScore())
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateGuestScoreSum(List<Event> teamEvents, final String teamName) {
        logger.info("calculateGuestScoreSum {} {}", teamName, teamEvents);
        Function<Event, Integer> mapScoreEvent = (Event event) -> {
            if(event.getGuestTeam().equals(teamName)) {
                return event.getGuestTeamScore();
            }
            return 0;
        };

        int sum = teamEvents.stream()
                .map(mapScoreEvent)
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateReceivedSum(List<Event> teamEvents) {
        throw new RuntimeException("not implemented");
    }


    public int calculateReceivedSum(List<Event> teamEvents, final String teamName) {
        logger.info("calculateReceivedSum {} {}", teamName, teamEvents);
        Function<Event, Integer> mapScoreEvent = (Event event) -> {
            if(event.getHomeTeam().equals(teamName)) {
                return event.getGuestTeamScore();
            }
            if(event.getGuestTeam().equals(teamName)) {
                return event.getHomeTeamScore();
            }
            return 0;
        };
        int sum = teamEvents.stream()
                .map(mapScoreEvent)
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }


    public int calculateHomeReceivedScoreSum(List<Event> teamEvents) {
        return 0;
    }


    public int calculateHomeReceivedScoreSum(List<Event> teamEvents, final String teamName) {
        logger.info("calculateHomeReceivedScoreSum {} {}", teamName, teamEvents);
        Function<Event, Integer> mapScoreEvent = (Event event) -> {
            if(event.getHomeTeam().equals(teamName)) {
                return event.getGuestTeamScore();
            }
            return 0;
        };

        int sum = teamEvents.stream()
                .map(mapScoreEvent)
                .reduce(SUM_ZERO, Integer::sum);
        logger.info("sum {}", sum);
        return sum;
    }



    public int calculateGuestReceivedScoreSum(List<Event> teamEvents) {
        return 0;
    }



    public int calculateGuestReceivedScoreSum(List<Event> teamEvents, final String teamName) {
        logger.info("calculateGuestReceivedScoreSum {} {}", teamName, teamEvents);
        Function<Event, Integer> mapScoreEvent = (Event event) -> {
            if(event.getGuestTeam().equals(teamName)) {
                return event.getHomeTeamScore();
            }
            return 0;
        };

         int sum = teamEvents.stream()
                .map(mapScoreEvent)
                .reduce(SUM_ZERO, Integer::sum);
         logger.info("sum {}", sum);
         return sum;
    }
}
