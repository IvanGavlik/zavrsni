package hr.vsite.igavlik.zavrsnirad.repository;

import hr.vsite.igavlik.zavrsnirad.entity.Event;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, UUID> {

    List<Event> findAllByHomeTeamOrGuestTeamOrderByEventDayDesc(Team homeTeam, Team guestTeam);

    Page<Event> findAllByHomeTeamOrGuestTeam(Pageable pageable, Team homeTeam, Team guestTeam);

    Page<Event> findAllByHomeTeam(Pageable pageable, Team homeTeam);

    Page<Event> findAllByGuestTeam(Pageable pageable, Team guestTeam);

}
