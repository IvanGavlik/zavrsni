package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.dto.EventDto;
import hr.vsite.igavlik.zavrsnirad.dto.v2.ChartWrapperDto;
import hr.vsite.igavlik.zavrsnirad.dto.v2.StatisticalCharacteristics;
import hr.vsite.igavlik.zavrsnirad.dto.v2.TableItemWrapperDto;
import hr.vsite.igavlik.zavrsnirad.entity.Event;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.service.EventService;
import hr.vsite.igavlik.zavrsnirad.service.PoissonFilterGuestService;
import hr.vsite.igavlik.zavrsnirad.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPoissonFilterGuestService implements PoissonFilterGuestService {

    private TeamService teamService;
    private EventService eventService;

    public DefaultPoissonFilterGuestService(TeamService teamService, EventService eventService) {
        this.teamService = teamService;
        this.eventService = eventService;
    }

    @Override
    public StatisticalCharacteristics calculateStatisticalCharacteristics(String homeTeamId, String guestTeamId) {
        Optional<Team> homeTeamOptional = teamService.getTeamById(homeTeamId);
        homeTeamOptional.orElseThrow(() -> new RuntimeException("Not found home team"));
        Optional<Team> guestTeamOptional = teamService.getTeamById(guestTeamId);
        guestTeamOptional.orElseThrow(() -> new RuntimeException("Not found guest team"));

        Team homeTeam = homeTeamOptional.get();
        Team guestTeam = guestTeamOptional.get();

        List<EventDto> homeTeamEvents = eventService.findAllEventsByTeam(homeTeam.getId().toString());
        List<EventDto> guestTeamEvents = eventService.findAllEventsByTeam(guestTeam.getId().toString());

        return new StatisticalCharacteristics(createChart(homeTeamEvents, guestTeamEvents), createTable(homeTeamEvents, guestTeamEvents));
    }

    private ChartWrapperDto createChart(List<EventDto> homeTeamEvents, List<EventDto> guestTeamEvents) {
        //return new ChartWrapperDto();
        return null;
    }

    private List<TableItemWrapperDto> createTable(List<EventDto> homeTeamEvents, List<EventDto> guestTeamEvents) {
        return null;
    }
}
