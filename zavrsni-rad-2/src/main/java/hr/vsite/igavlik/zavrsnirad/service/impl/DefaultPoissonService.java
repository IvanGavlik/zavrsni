package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.dto.*;
import hr.vsite.igavlik.zavrsnirad.entity.GoalProbability;
import hr.vsite.igavlik.zavrsnirad.entity.PoissonGuest;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.service.*;
import hr.vsite.igavlik.zavrsnirad.service.v3.formula.FormulaService;
import hr.vsite.igavlik.zavrsnirad.v1.PoissonController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DefaultPoissonService implements PoissonService {

    private static Logger logger = LoggerFactory.getLogger(PoissonController.class);

    private static final String NUMBER_OF_GOALS = "Količina golova ";

    private TeamService teamService;
    private EventService eventService;
    private FormulaService formulaService;
    private PoissonGuestService poissonGuestService;

    @Autowired
    public DefaultPoissonService(TeamService teamService, EventService eventService, FormulaService formulaService, PoissonGuestService poissonGuestService) {
        this.teamService = teamService;
        this.eventService = eventService;
        this.formulaService = formulaService;
        this.poissonGuestService = poissonGuestService;
    }


    @Override
    @Transactional
    public PoissonDto calculatePoisson(String homeTeamId, String guestTeamId) {
        Optional<Team> homeTeamOptional = teamService.getTeamById(homeTeamId);
        homeTeamOptional.orElseThrow(() -> new RuntimeException("Not implemented"));
        Optional<Team> guestTeamOptional = teamService.getTeamById(guestTeamId);
        guestTeamOptional.orElseThrow(() -> new RuntimeException("Not implemented"));

        Team homeTeam = homeTeamOptional.get();
        Team guestTeam = guestTeamOptional.get();

        List<EventDto> homeTeamEvents = eventService.findAllEventsByTeam(homeTeam.getId().toString());
        List<EventDto> guestTeamEvents = eventService.findAllEventsByTeam(guestTeam.getId().toString());

        final String homeTeamName = homeTeam.getName();
        StatisticalFeatureChartDto chartDtoHomeTeam = getStatisticalFeatureChart(homeTeamName, homeTeamEvents);
        final String guestTeamName = guestTeam.getName();
        StatisticalFeatureChartDto chartDtoGuestTeam = getStatisticalFeatureChart(guestTeamName, guestTeamEvents);

        List<StatisticalFeatureTableItemDto> statisticalFeatureTableHomeTeam = getStatisticalFeatureTable(homeTeamName, homeTeamEvents);
        List<StatisticalFeatureTableItemDto> statisticalFeatureTableGuestTeam = getStatisticalFeatureTable(guestTeamName, guestTeamEvents);

        PoissonStatisticsTeamDto homeTeamDto = new PoissonStatisticsTeamDto(homeTeamName, chartDtoHomeTeam, statisticalFeatureTableHomeTeam);
        PoissonStatisticsTeamDto guestTeamDto = new PoissonStatisticsTeamDto(guestTeamName, chartDtoGuestTeam, statisticalFeatureTableGuestTeam);

        PoissonMatrixDto poissonMatrix = calculatePoissonMatrix(homeTeam, statisticalFeatureTableHomeTeam, guestTeam, statisticalFeatureTableGuestTeam);

        PoissonDto poissonDto = new PoissonDto(homeTeamDto, guestTeamDto, poissonMatrix);
        logger.info("calculatePoisson poissonDto {}", poissonDto);
        return poissonDto;
    }

    @Override
    @Transactional
    public PoissonGuessDto whoWillWin(String homeTeamId, String guestTeamId) {
        Optional<Team> homeTeam = teamService.getTeamById(homeTeamId);
        Optional<Team> guessTeam = teamService.getTeamById(guestTeamId);

        if (homeTeam.isPresent() && guessTeam.isPresent()) {
            Optional<PoissonGuest> homeTeamPoissonGuest = poissonGuestService.findByTeam(homeTeam.get());
            Optional<PoissonGuest> guestTeamPoissonGuest = poissonGuestService.findByTeam(guessTeam.get());

            if (homeTeamPoissonGuest.isPresent() && guestTeamPoissonGuest.isPresent()) {
                PoissonGuessDto poissonGuessDto = whoWillWinUtil(homeTeamPoissonGuest.get(), guestTeamPoissonGuest.get());
                logger.info("whoWillWin PoissonGuessDto {}", poissonGuessDto);
                return poissonGuessDto;
            }
            throw new RuntimeException("Not found PoissonGuest");
        }
        throw new RuntimeException("Not found Team");
    }

    @Override
    @Transactional
    public PoissonGuessDto calculateHowManyGoals(String teamName, Operation operation, final Integer goalQuanity) {
        Optional<Team> optionalTeam = teamService.getTeamByName(teamName);
        optionalTeam.orElseThrow(() -> new RuntimeException("Team not found"));

        Optional<PoissonGuest> poissonGuestOptional = poissonGuestService.findByTeam(optionalTeam.get());
        poissonGuestOptional.orElseThrow(() -> new RuntimeException("PoissonGuest not found"));
        logger.info("calculateHowManyGoals poissonGuest {}" + poissonGuestOptional.get());

        Predicate<GoalProbability> goalPredicate = null;
        switch (operation) {
            case LESS:
                goalPredicate = el -> el.getGoal() < goalQuanity;
                break;
            case SAME:
                goalPredicate = el -> el.getGoal().equals(goalQuanity);
                break;
            case MORE:
                goalPredicate = el -> el.getGoal() > goalQuanity;
                break;
            default:
                throw new RuntimeException("goalPredicate operation not implemented");
        }

        List<Double> probabilityes = poissonGuestOptional.get().getGoalProbability().stream()
                .sorted((o1, o2) -> o1.getGoal() - o2.getGoal())
                .filter(goalPredicate)
                .map(el -> el.getProbability())
                .collect(Collectors.toList());
        logger.info("calculateHowManyGoals probabilityes {}" + probabilityes);

        AtomicReference<Double> result = new AtomicReference<>(0.0);
        probabilityes.forEach(el -> result.updateAndGet(v -> v + el));

        final String guessFormat = "Vjerojatnost da ce %s zabiti %s %s je %.5f";
        String guess = String.format(guessFormat,
                optionalTeam.get().getName(), operation, goalQuanity, result.get()
        );
        guess = guess.replace("LESS", "manje");
        guess = guess.replace("SAME", "jednako");
        guess = guess.replace("MORE", "više");
        guess = guess.replace(",", ".");

        PoissonGuessDto poissonGuessDto = new PoissonGuessDto(guess, null);
        logger.info("poissonGuessDto {}", poissonGuessDto);
        return poissonGuessDto;
    }

    /**
     * Sorting is decs by PROBABILITY if same sort by quantity of goals*
     * <p>
     * quantity of goals - how many goals team will score
     */
    private final Comparator<GoalProbability> PROBABILITY_GOAL_COMPARATOR = (o1, o2) -> {
        Double p = o2.getProbability() - o1.getProbability();
        if (p == 0.0) {
            return o2.getGoal() - o1.getGoal();
        }
        return new Double(p * 1000).intValue();
    };

    /**
     * Best GoalProbability is determined by biggest probability for scoring n goals
     * If team have same probability for scoring n goals, algorithm will take the highest
     * <p>
     * Example:
     * Team A have the same probability for scoring 2 goals as for the 1, so prediction is: Team A will score 2 goals
     * Team B have the biggest probability for scoring 3 goals, so prediction is that Team B will score 3 goals
     * Winner is team that have biggest probability for scoring more goals. In this case this is Team B
     *
     * @param poissonGuestHomeTeam
     * @param poissonGuestGuestHomeTeam
     * @return PoissonGuessDto (how will win)
     */
    public PoissonGuessDto whoWillWinUtil(PoissonGuest poissonGuestHomeTeam, PoissonGuest poissonGuestGuestHomeTeam) {
        Optional<GoalProbability> goalHomeTeamOptional = poissonGuestHomeTeam.getGoalProbability()
                .stream()
                .sorted(PROBABILITY_GOAL_COMPARATOR)
                .findFirst();
        logger.info("whoWillWinUtil homeTeam best GoalProbability {}", goalHomeTeamOptional);

        Optional<GoalProbability> goalGuessTeamOptional = poissonGuestGuestHomeTeam.getGoalProbability()
                .stream()
                .sorted(PROBABILITY_GOAL_COMPARATOR)
                .findFirst();
        logger.info("whoWillWinUtil guessTeam best GoalProbability {}", goalGuessTeamOptional);

        final GoalProbability goalHomeTeam = goalHomeTeamOptional.get();
        final GoalProbability goalGuessTeam = goalGuessTeamOptional.get();

        final String guessFormat = "Najvjerojatniji rezultat je: %s %s : %s %s";
        final String guess = String.format(guessFormat,
                poissonGuestHomeTeam.getTeam().getName(), goalHomeTeam.getGoal(),
                poissonGuestGuestHomeTeam.getTeam().getName(), goalGuessTeam.getGoal()
        );
        final String explanationFormat = "Jer je najvjerojatnije da ce %s zabiti %s sa vjerojatnosti %.5f, a %s %s sa vjerojatnosti %.5f";
        final String explanation = String.format(explanationFormat,
                poissonGuestHomeTeam.getTeam().getName(), goalHomeTeam.getGoal(), goalHomeTeam.getProbability(),
                poissonGuestGuestHomeTeam.getTeam().getName(), goalGuessTeam.getGoal(), goalGuessTeam.getProbability()
        ).replace(",", ".");
        return new PoissonGuessDto(guess, explanation);
    }

    private StatisticalFeatureChartDto getStatisticalFeatureChart(final String teamName, List<EventDto> eventDtos) {
        Function<EventDto, ChartItemDto> mapGoal = el -> {
            Integer goal = 0;
            if (teamName.equals(el.getHomeTeamName())) {
                goal = el.getHomeTeamScore();
            } else if (teamName.equals(el.getGuestTeamName())) {
                goal = el.getGuestTeamScore();
            } else {
                throw new RuntimeException("getStatisticalFeatureChart get team goal");
            }
            return new ChartItemDto(Integer.valueOf(el.getRound()), goal);
        };

        List<ChartItemDto> chartItemDtos =
                eventDtos.stream()
                        .map(mapGoal)
                        .sorted((o1, o2) -> o2.getLabel() - o1.getLabel())
                        .collect(Collectors.toList());

        return new StatisticalFeatureChartDto(NUMBER_OF_GOALS + teamName, chartItemDtos);
    }

    private List<StatisticalFeatureTableItemDto> getStatisticalFeatureTable(final String teamName, List<EventDto> eventDtos) {
        List<StatisticalFeatureTableItemDto> itemDtos = new ArrayList<>();

        for (int i = 0; i < 6; i++) {

            final Integer goal = new Integer(i);
            final Long frequency = eventDtos.stream().
                    filter(el -> {
                        if (teamName.equals(el.getHomeTeamName()) && goal.equals(el.getHomeTeamScore())) {
                            return true;
                        }
                        if (teamName.equals(el.getGuestTeamName()) && goal.equals(el.getGuestTeamScore())) {
                            return true;
                        }
                        return false;
                    })
                    .count();

            final Double relativeFrequency = formulaService.relativeFrequency(frequency.intValue(), eventDtos.size());
            final Double arithmeticMiddle = formulaService.arithmeticMiddle(goal, relativeFrequency);
            final Double dispersion = formulaService.dispersion(goal, relativeFrequency);
            final Double deviation = formulaService.deviation(dispersion);

            StatisticalFeatureTableItemDto tableItemDto =
                    new StatisticalFeatureTableItemDto(goal, frequency.intValue(), relativeFrequency, arithmeticMiddle, dispersion, deviation);
            itemDtos.add(tableItemDto);
            logger.info("getStatisticalFeatureTable add item {}" + tableItemDto);
        }

        return itemDtos;
    }

    private static final int GOAL_0 = 0;
    private static final int GOAL_1 = 1;
    private static final int GOAL_2 = 2;
    private static final int GOAL_3 = 3;
    private static final int GOAL_4 = 4;
    private static final int GOAL_5 = 5;

    private PoissonMatrixDto calculatePoissonMatrix(final Team homeTeam, List<StatisticalFeatureTableItemDto> statisticalFeatureTableHomeTeam, final Team guestTeam, List<StatisticalFeatureTableItemDto> statisticalFeatureTableGuestTeam) {
        Matrix5x5Dto matrixDto = new Matrix5x5Dto();

        final Double homeTeamAverage = statisticalFeatureTableHomeTeam.stream().map(StatisticalFeatureTableItemDto::getArithmeticMiddle).reduce(0.0, Double::sum);
        final Double guestTeamAverage = statisticalFeatureTableGuestTeam.stream().map(StatisticalFeatureTableItemDto::getArithmeticMiddle).reduce(0.0, Double::sum);

        Double homeTeamPoissonForGoal0 = formulaService.calculatePoisson(homeTeamAverage, GOAL_0);
        Double homeTeamPoissonForGoal1 = formulaService.calculatePoisson(homeTeamAverage, GOAL_1);
        Double homeTeamPoissonForGoal2 = formulaService.calculatePoisson(homeTeamAverage, GOAL_2);
        Double homeTeamPoissonForGoal3 = formulaService.calculatePoisson(homeTeamAverage, GOAL_3);
        Double homeTeamPoissonForGoal4 = formulaService.calculatePoisson(homeTeamAverage, GOAL_4);
        Double homeTeamPoissonForGoal5 = formulaService.calculatePoisson(homeTeamAverage, GOAL_5);

        poissonGuestService.savePoissonGuest(homeTeam, new GoalProbability(GOAL_0, homeTeamPoissonForGoal0),
                new GoalProbability(GOAL_1, homeTeamPoissonForGoal1),
                new GoalProbability(GOAL_2, homeTeamPoissonForGoal2),
                new GoalProbability(GOAL_3, homeTeamPoissonForGoal3),
                new GoalProbability(GOAL_4, homeTeamPoissonForGoal4),
                new GoalProbability(GOAL_5, homeTeamPoissonForGoal5));

        Double guestTeamPoissonForGoal0 = formulaService.calculatePoisson(guestTeamAverage, GOAL_0);
        Double guestTeamPoissonForGoal1 = formulaService.calculatePoisson(guestTeamAverage, GOAL_1);
        Double guestTeamPoissonForGoal2 = formulaService.calculatePoisson(guestTeamAverage, GOAL_2);
        Double guestTeamPoissonForGoal3 = formulaService.calculatePoisson(guestTeamAverage, GOAL_3);
        Double guestTeamPoissonForGoal4 = formulaService.calculatePoisson(guestTeamAverage, GOAL_4);
        Double guestTeamPoissonForGoal5 = formulaService.calculatePoisson(guestTeamAverage, GOAL_5);

        poissonGuestService.savePoissonGuest(guestTeam, new GoalProbability(GOAL_0, guestTeamPoissonForGoal0),
                new GoalProbability(GOAL_1, guestTeamPoissonForGoal1),
                new GoalProbability(GOAL_2, guestTeamPoissonForGoal2),
                new GoalProbability(GOAL_3, guestTeamPoissonForGoal3),
                new GoalProbability(GOAL_4, guestTeamPoissonForGoal4),
                new GoalProbability(GOAL_5, guestTeamPoissonForGoal5));

        matrixDto.setIndex00(homeTeamPoissonForGoal0 * guestTeamPoissonForGoal0);
        matrixDto.setIndex01(homeTeamPoissonForGoal0 * guestTeamPoissonForGoal1);
        matrixDto.setIndex02(homeTeamPoissonForGoal0 * guestTeamPoissonForGoal2);
        matrixDto.setIndex03(homeTeamPoissonForGoal0 * guestTeamPoissonForGoal3);
        matrixDto.setIndex04(homeTeamPoissonForGoal0 * guestTeamPoissonForGoal4);
        matrixDto.setIndex05(homeTeamPoissonForGoal0 * guestTeamPoissonForGoal5);

        matrixDto.setIndex10(homeTeamPoissonForGoal1 * guestTeamPoissonForGoal0);
        matrixDto.setIndex11(homeTeamPoissonForGoal1 * guestTeamPoissonForGoal1);
        matrixDto.setIndex12(homeTeamPoissonForGoal1 * guestTeamPoissonForGoal2);
        matrixDto.setIndex13(homeTeamPoissonForGoal1 * guestTeamPoissonForGoal3);
        matrixDto.setIndex14(homeTeamPoissonForGoal1 * guestTeamPoissonForGoal4);
        matrixDto.setIndex15(homeTeamPoissonForGoal1 * guestTeamPoissonForGoal5);

        matrixDto.setIndex20(homeTeamPoissonForGoal2 * guestTeamPoissonForGoal0);
        matrixDto.setIndex21(homeTeamPoissonForGoal2 * guestTeamPoissonForGoal1);
        matrixDto.setIndex22(homeTeamPoissonForGoal2 * guestTeamPoissonForGoal2);
        matrixDto.setIndex23(homeTeamPoissonForGoal2 * guestTeamPoissonForGoal3);
        matrixDto.setIndex24(homeTeamPoissonForGoal2 * guestTeamPoissonForGoal4);
        matrixDto.setIndex25(homeTeamPoissonForGoal2 * guestTeamPoissonForGoal5);

        matrixDto.setIndex30(homeTeamPoissonForGoal3 * guestTeamPoissonForGoal0);
        matrixDto.setIndex31(homeTeamPoissonForGoal3 * guestTeamPoissonForGoal1);
        matrixDto.setIndex32(homeTeamPoissonForGoal3 * guestTeamPoissonForGoal2);
        matrixDto.setIndex33(homeTeamPoissonForGoal3 * guestTeamPoissonForGoal3);
        matrixDto.setIndex34(homeTeamPoissonForGoal3 * guestTeamPoissonForGoal4);
        matrixDto.setIndex35(homeTeamPoissonForGoal3 * guestTeamPoissonForGoal5);

        matrixDto.setIndex40(homeTeamPoissonForGoal4 * guestTeamPoissonForGoal0);
        matrixDto.setIndex41(homeTeamPoissonForGoal4 * guestTeamPoissonForGoal1);
        matrixDto.setIndex42(homeTeamPoissonForGoal4 * guestTeamPoissonForGoal2);
        matrixDto.setIndex43(homeTeamPoissonForGoal4 * guestTeamPoissonForGoal3);
        matrixDto.setIndex44(homeTeamPoissonForGoal4 * guestTeamPoissonForGoal4);
        matrixDto.setIndex45(homeTeamPoissonForGoal4 * guestTeamPoissonForGoal5);

        matrixDto.setIndex50(homeTeamPoissonForGoal5 * guestTeamPoissonForGoal0);
        matrixDto.setIndex51(homeTeamPoissonForGoal5 * guestTeamPoissonForGoal1);
        matrixDto.setIndex52(homeTeamPoissonForGoal5 * guestTeamPoissonForGoal2);
        matrixDto.setIndex53(homeTeamPoissonForGoal5 * guestTeamPoissonForGoal3);
        matrixDto.setIndex54(homeTeamPoissonForGoal5 * guestTeamPoissonForGoal4);
        matrixDto.setIndex55(homeTeamPoissonForGoal5 * guestTeamPoissonForGoal5);

        PoissonMatrixDto poissonMatrixDto = new PoissonMatrixDto(homeTeam.getName(), guestTeam.getName(), matrixDto);
        logger.info("calculatePoissonMatrix {}", poissonMatrixDto);
        return poissonMatrixDto;
    }

}
