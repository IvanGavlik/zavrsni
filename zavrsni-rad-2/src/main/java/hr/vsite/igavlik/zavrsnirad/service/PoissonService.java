package hr.vsite.igavlik.zavrsnirad.service;

import hr.vsite.igavlik.zavrsnirad.dto.PoissonDto;
import hr.vsite.igavlik.zavrsnirad.dto.PoissonGuessDto;


public interface PoissonService {

    PoissonDto calculatePoisson(String homeTeamId, String guestTeamId);

    PoissonGuessDto whoWillWin(String homeTeamId, String guestTeamId);

    enum Operation {
        LESS, SAME, MORE
    }

    PoissonGuessDto calculateHowManyGoals(String teamName, Operation operation, Integer goalQuanity);
}
