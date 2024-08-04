package hr.vsite.igavlik.zavrsnirad.v2;

import hr.vsite.igavlik.zavrsnirad.service.EventFilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EventFilterController {

    private static Logger logger = LoggerFactory.getLogger(EventFilterController.class);

    private final EventFilterService eventFilterService;

    @Autowired
    public EventFilterController(final EventFilterService eventFilterService) {
        this.eventFilterService = eventFilterService;
    }

    @GetMapping("v2/event")
    public ResponseEntity getAllEventsByTeam(@RequestParam(value = "teamId") String teamId,
                                             @RequestParam(value = "eventPlace") String eventPlace,
                                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        logger.info("v2/event/?teamId={}  eventPlace {} pageIndex {} pageSize {}", teamId, eventPlace, pageIndex, pageSize);
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v2/event");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(eventFilterService.findAllEventsByTeamPageable(teamId, EventFilterService.EventPlace.valueOf(eventPlace), pageIndex, pageSize));
    }
}
