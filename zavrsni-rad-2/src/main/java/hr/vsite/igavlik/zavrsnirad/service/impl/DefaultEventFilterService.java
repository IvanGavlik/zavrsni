package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.dto.EventDto;
import hr.vsite.igavlik.zavrsnirad.dto.PageableDto;
import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureChartDto;
import hr.vsite.igavlik.zavrsnirad.entity.Event;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.repository.EventRepository;
import hr.vsite.igavlik.zavrsnirad.service.EventFilterService;
import hr.vsite.igavlik.zavrsnirad.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultEventFilterService implements EventFilterService {

    private static Logger logger = LoggerFactory.getLogger(DefaultEventFilterService.class);

    private TeamService teamService;
    private EventRepository eventRepository;

    @Autowired
    public DefaultEventFilterService(TeamService teamService, EventRepository eventRepository) {
        this.teamService = teamService;
        this.eventRepository = eventRepository;
    }

    @Override
    public PageableDto<EventDto> findAllEventsByTeamPageable(String teamId, EventPlace eventPlace, int pageIndex, int pageSize) {
        Optional<Team> optionalTeam = teamService.getTeamById(teamId);
        optionalTeam.orElseThrow(() -> new RuntimeException("Not found team"));

        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("eventDay").descending());
        Page<Event> page = null;
        switch (eventPlace) {
            case HOME:
                page = eventRepository.findAllByHomeTeam(pageable, optionalTeam.get());
                break;
            case GUEST:
                page = eventRepository.findAllByGuestTeam(pageable, optionalTeam.get());
                break;
            default:
                new RuntimeException("Not implemented");
        }

        List<EventDto> eventDtoLis = page.getContent()
                .stream()
                .map((Event e) -> new EventDto(e))
                .collect(Collectors.toList());

        PageableDto<EventDto> pageableDto = PageableDto.<EventDto>builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .pageItems(eventDtoLis)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
        logger.info("findAllEventsByTeamPageable pageableDto {}", pageableDto);
        return pageableDto;
    }

    @Override
    public StatisticalFeatureChartDto createChart(String teamId) {
        return null;
    }
}
