package hr.vsite.igavlik.zavrsnirad.repository;

import hr.vsite.igavlik.zavrsnirad.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TeamRepository extends CrudRepository<Team, UUID> {

    List<Team> findAll();

    Optional<Team> getByName(String name);

    Optional<Team> getAllById(UUID uuid);
}
