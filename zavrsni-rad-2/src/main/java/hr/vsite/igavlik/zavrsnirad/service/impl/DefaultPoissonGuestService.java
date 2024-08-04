package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.entity.GoalProbability;
import hr.vsite.igavlik.zavrsnirad.entity.PoissonGuest;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.repository.PoissonGuestRepository;
import hr.vsite.igavlik.zavrsnirad.service.PoissonGuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DefaultPoissonGuestService implements PoissonGuestService {

    private PoissonGuestRepository poissonGuestRepository;

    @Autowired
    public DefaultPoissonGuestService(PoissonGuestRepository poissonGuestRepository) {
        this.poissonGuestRepository = poissonGuestRepository;
    }

    @Override
    @Transactional
    public void savePoissonGuest(Team team, GoalProbability gp) {
        PoissonGuest poissonGuest = null;
        Optional<PoissonGuest> optionalPoissonGuest = this.poissonGuestRepository.findByTeam(team);

        if (optionalPoissonGuest.isPresent()) {
            poissonGuest = optionalPoissonGuest.get();
            if(!(poissonGuest.getGoalProbability().add(gp))) {
                poissonGuest.getGoalProbability().iterator().next().setProbability(gp.getProbability());
            }
        } else {
            poissonGuest = new PoissonGuest();
            poissonGuest.setTeam(team);
            poissonGuest.getGoalProbability().add(gp);
        }

        this.poissonGuestRepository.save(poissonGuest);
    }

    @Override
    @Transactional
    public void savePoissonGuest(Team team, GoalProbability... gp2) {
        for(int i = 0; i < gp2.length; i++) {
            savePoissonGuest(team, gp2[i]);
        }
    }

    @Override
    public Optional<PoissonGuest> findByTeam(Team team) {
        return poissonGuestRepository.findByTeam(team);
    }
}
