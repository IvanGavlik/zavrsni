package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.dto.PoissonGuessDto;
import hr.vsite.igavlik.zavrsnirad.entity.GoalProbability;
import hr.vsite.igavlik.zavrsnirad.entity.PoissonGuest;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.service.*;
import hr.vsite.igavlik.zavrsnirad.service.v3.formula.FormulaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashSet;

public class DefaultPoissonServiceTest {

    private PoissonService poissonService;

    @Mock
    private TeamService teamService;
    @Mock
    private EventService eventService;
    @Mock
    private FormulaService formulaService;
    @Mock
    private PoissonGuestService poissonGuestService;

    @BeforeEach
    public void setInit() {
        poissonService = new DefaultPoissonService(teamService, eventService, formulaService, poissonGuestService);
    }

    /**
     * todo write tests, not working
     */
    @Test
    public void testAll() {
        Team ht = new Team();
        HashSet<GoalProbability> probabilities = new HashSet<>();
        probabilities.add(new GoalProbability(1, 1.0));
        probabilities.add(new GoalProbability(2, 2.2));
//        probabilities.add(new GoalProbability(3, 1.1));
        PoissonGuest poissonGuestHomeTeam = new PoissonGuest();
        poissonGuestHomeTeam.setGoalProbability(probabilities);
        poissonGuestHomeTeam.setTeam(ht);

        Team ht2 = new Team();
        HashSet<GoalProbability> probabilities2 = new HashSet<>();
        probabilities2.add(new GoalProbability(1, 1.0));
        probabilities2.add(new GoalProbability(2, 2.0));
        probabilities2.add(new GoalProbability(3, 4.1));

        PoissonGuest poissonGuestGuestTeam = new PoissonGuest();
        poissonGuestGuestTeam.setGoalProbability(probabilities2);
        poissonGuestGuestTeam.setTeam(ht2);

        PoissonGuessDto res = poissonService.whoWillWin(ht.getId().toString(), ht2.getId().toString());
        System.out.println("msg::" +res.getPoissonGuess() + res.getExplanation());

        assert (true);
    }
}
