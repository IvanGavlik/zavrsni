package hr.vsite.igavlik.zavrsnirad.repository;

import hr.vsite.igavlik.zavrsnirad.entity.PoissonGuest;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PoissonGuestRepository extends CrudRepository<PoissonGuest, UUID> {

    Optional<PoissonGuest> findByTeam(Team team);
}
