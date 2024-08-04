package hr.vsite.igavlik.zavrsnirad.service.v3.simulation;

import hr.vsite.igavlik.zavrsnirad.dto.v3.PoissonCard;
import hr.vsite.igavlik.zavrsnirad.service.v3.match.MatchPredictService;
import hr.vsite.igavlik.zavrsnirad.service.v3.match.MatchPredictServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SimulationImpl implements Simulation {

    private static Logger logger = LoggerFactory.getLogger(MatchPredictServiceImpl.class);

    private MatchPredictService matchPredictService;
    private Map<Integer, String> teamsByIndex;
    private Map<Integer, List<Integer>> randomByIndex;

    @Autowired
    public SimulationImpl(MatchPredictService matchPredictService) {
        this.matchPredictService = matchPredictService;
        this.teamsByIndex = this.initTeamsByIndex();
        this.randomByIndex = this.initRandomByIndex();
    }

    @Override
    public List<PoissonCard> simulate() {
        List<PoissonCard> games = new ArrayList<>();

        int randomInt = ThreadLocalRandom.current().nextInt(0, 12);
        logger.warn("simulate randomInt {}", randomInt);
        List<Integer> randomGamesIndex = this.randomByIndex.get(randomInt);

        String home = null;
        int i = 0;
        String gues = null;
        for (Integer index: randomGamesIndex) {
            i = i +1;

            if(i  % 2 != 0) {
                home = this.teamsByIndex.get(index);
            }

            if(i  % 2 == 0) {
                gues = this.teamsByIndex.get(index);
                games.add(matchPredictService.getPoissonCard(home, gues));
            }
        }

        this.teamsByIndex = this.initTeamsByIndex();
        return games;
    }

    private Map<Integer, String> initTeamsByIndex() {
        Map<Integer, String> teamsByIndex = new HashMap<>();
        teamsByIndex.put(0, "Rangers FC");
        teamsByIndex.put(1, "Celtic FC");
        teamsByIndex.put(2, "Aberdeen FC");
        teamsByIndex.put(3, "Hibernian FC");
        teamsByIndex.put(4, "Kilmarnock FC");
        teamsByIndex.put(5, "Motherwell FC");
        teamsByIndex.put(6, "Heart of Midlothian FC");
        teamsByIndex.put(7, "Livingston FC");
        teamsByIndex.put(8, "St Johnstone FC");
        teamsByIndex.put(9, "Saint Mirren FC");
        teamsByIndex.put(10, "Dundee FC");
        teamsByIndex.put(11, "Hamilton Academical FC");
        return teamsByIndex;
    }

    private Map<Integer, List<Integer>> initRandomByIndex() {
        Map<Integer, List<Integer>> randomByIndex = new HashMap<>();
        randomByIndex.put(0, generateRandomGameIndex1());
        randomByIndex.put(1, generateRandomGameIndex2());
        randomByIndex.put(2, generateRandomGameIndex3());
        randomByIndex.put(3, generateRandomGameIndex4());
        randomByIndex.put(4, generateRandomGameIndex5());
        randomByIndex.put(5, generateRandomGameIndex6());
        randomByIndex.put(6, generateRandomGameIndex7());
        randomByIndex.put(7, generateRandomGameIndex8());
        randomByIndex.put(8, generateRandomGameIndex9());
        randomByIndex.put(9, generateRandomGameIndex10());
        randomByIndex.put(10, generateRandomGameIndex11());
        randomByIndex.put(11, generateRandomGameIndex12());
        return randomByIndex;
    }

    private List<Integer> generateRandomGameIndex1() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 2, 3, 11, 9, 1, 0, 7, 10, 6, 4, 5, 8};
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex2() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 0, 4, 1, 9, 11, 3, 8, 6, 7, 5, 10, 2 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex3() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 0, 3, 5, 1, 9, 2, 6, 10, 8, 7, 11, 4 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex4() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 11, 6, 1, 0, 10, 5, 7, 3, 8, 9, 4, 2 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex5() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 9, 3, 11, 6, 1, 4, 7, 2, 10, 0, 5, 8 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex6() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 11, 5, 0, 7, 9, 1, 6, 3, 10, 8, 4, 2 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex7() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 0, 11, 8, 4, 10, 9, 7, 2, 1, 5, 6, 3 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex8() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 0, 3, 5, 6, 9, 7, 11, 4, 1, 10, 8, 2 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex9() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 4, 7, 11, 6, 9, 3, 0, 10, 2, 5, 8, 1 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex10() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = {4 ,8, 11, 2, 0, 1, 7, 3, 9, 10, 6, 5 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex11() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = { 10, 3, 11, 2, 8, 9, 1, 5, 4, 0, 7, 6 };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

    private List<Integer> generateRandomGameIndex12() {
        List<Integer> randomGameIndex = new ArrayList<>();
        Integer[] random = {3, 8, 11, 5, 6, 0, 4, 9, 7, 10, 1, 2  };
        randomGameIndex.addAll(Arrays.asList(random));
        return randomGameIndex;
    }

}
