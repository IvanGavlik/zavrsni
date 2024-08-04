package hr.vsite.igavlik.zavrsnirad.service;

import hr.vsite.igavlik.zavrsnirad.dto.EventDto;
import hr.vsite.igavlik.zavrsnirad.dto.PageableDto;
import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureChartDto;

import java.util.List;

public interface EventService {

    PageableDto<EventDto> findAllEventsPageable(final int pageIndex, final int pageSize);

    PageableDto<EventDto> findAllEventsByTeamPageable(String teamId, final int pageIndex, final int pageSize);

    EventDto addNewEvent(EventDto eventDto);

    List<EventDto> findAllEventsByTeam(String teamId);

}
