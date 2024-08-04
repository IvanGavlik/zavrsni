package hr.vsite.igavlik.zavrsnirad.service;

import hr.vsite.igavlik.zavrsnirad.dto.EventDto;
import hr.vsite.igavlik.zavrsnirad.dto.PageableDto;
import hr.vsite.igavlik.zavrsnirad.dto.StatisticalFeatureChartDto;

public interface EventFilterService {

    PageableDto<EventDto> findAllEventsByTeamPageable(String teamId, EventPlace eventPlace, final int pageIndex, final int pageSize);

    enum EventPlace {
        HOME, GUEST
    }

    StatisticalFeatureChartDto createChart(String teamId);
}
