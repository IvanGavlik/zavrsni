package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.dto.EventDto;
import hr.vsite.igavlik.zavrsnirad.dto.PageableDto;
import hr.vsite.igavlik.zavrsnirad.entity.Event;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.repository.EventRepository;
import hr.vsite.igavlik.zavrsnirad.service.EventService;
import hr.vsite.igavlik.zavrsnirad.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultEventService implements EventService {

    private static Logger logger = LoggerFactory.getLogger(DefaultEventService.class);

    private TeamService teamService;
    private EventRepository eventRepository;

    @Autowired
    public DefaultEventService(TeamService teamService, EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
    }

    @Override
    @Transactional
    public EventDto addNewEvent(EventDto eventDto) {
        logger.info("addNewEvent {}", eventDto);
        if (eventDto.getHomeTeamName().equals(eventDto.getGuestTeamName())) {
            throw new RuntimeException("home and guest team can not be same");
        }
        Optional<Team> homeTeam = teamService.getTeamByName(eventDto.getHomeTeamName());
        homeTeam.orElseThrow(() -> new RuntimeException("Team not found"));
        System.out.println("HT::" + homeTeam.get().getId());
        Optional<Team> guestTeam = teamService.getTeamByName(eventDto.getGuestTeamName());
        guestTeam.orElseThrow(() -> new RuntimeException("Team not found"));

        Event event = new Event();
        event.setHomeTeam(homeTeam.get());
        event.setHomeTeamScore(eventDto.getHomeTeamScore());
        event.setGuestTeam(guestTeam.get());
        event.setGuestTeamScore(eventDto.getGuestTeamScore());
        event.setEventDay(eventDto.getEventDay());

        return new EventDto(eventRepository.save(event));
    }

    @Override
    @Transactional
    public List<EventDto> findAllEventsByTeam(String teamId) {
        Optional<Team> optionalTeam = teamService.getTeamById(teamId);
        optionalTeam.orElseThrow(() -> new RuntimeException("Not implemented"));

        return eventRepository.findAllByHomeTeamOrGuestTeamOrderByEventDayDesc(optionalTeam.get(), optionalTeam.get())
                .stream()
                .map((Event e) -> new EventDto(e))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PageableDto<EventDto> findAllEventsPageable(final int pageIndex, final int pageSize) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("eventDay").descending());
        Page<Event> page = eventRepository.findAll(pageable);

        List<EventDto> eventDtoList = page.getContent()
                .stream()
                .map(el -> new EventDto(el))
                .collect(Collectors.toList());

        PageableDto<EventDto> pageableDto = PageableDto.<EventDto>builder()
                .pageIndex(pageIndex)
                .pageSize(pageSize)
                .pageItems(eventDtoList)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();
        logger.info("findAllEventsPageable pageableDto {}", pageableDto);
        return pageableDto;
    }

    @Override
    @Transactional
    public PageableDto<EventDto> findAllEventsByTeamPageable(String teamId, int pageIndex, int pageSize) {
        Optional<Team> optionalTeam = teamService.getTeamById(teamId);
        optionalTeam.orElseThrow(() -> new RuntimeException("Not found team"));

        Pageable pageable = PageRequest.of(pageIndex, pageSize, Sort.by("eventDay").descending());
        Page<Event> page = eventRepository.findAllByHomeTeamOrGuestTeam(pageable, optionalTeam.get(), optionalTeam.get());

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
}
