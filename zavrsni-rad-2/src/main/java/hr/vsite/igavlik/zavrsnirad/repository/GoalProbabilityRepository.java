package hr.vsite.igavlik.zavrsnirad.repository;

import hr.vsite.igavlik.zavrsnirad.entity.GoalProbability;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GoalProbabilityRepository  extends CrudRepository<GoalProbability, UUID> {
}
