package hr.vsite.igavlik.zavrsnirad.service;

import hr.vsite.igavlik.zavrsnirad.entity.GoalProbability;
import hr.vsite.igavlik.zavrsnirad.entity.PoissonGuest;
import hr.vsite.igavlik.zavrsnirad.entity.Team;

import java.util.Optional;

public interface PoissonGuestService {

    void savePoissonGuest(Team team, GoalProbability gp2);

    void savePoissonGuest(Team team, GoalProbability... gp2);

    Optional<PoissonGuest> findByTeam(Team team);
}
