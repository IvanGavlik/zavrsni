package hr.vsite.igavlik.zavrsnirad.v1;

import hr.vsite.igavlik.zavrsnirad.dto.EventDto;
import hr.vsite.igavlik.zavrsnirad.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class EventController {

    private static Logger logger = LoggerFactory.getLogger(EventController.class);


    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("v1/event")
    public ResponseEntity newEvent(@RequestBody EventDto eventDto) {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/event");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(eventService.addNewEvent(eventDto));
    }


    @GetMapping("v1/event")
    public ResponseEntity getAllEventsByTeam(@RequestParam(value = "teamId") String teamId,
                                             @RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                             @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        logger.info("v1/event/?teamId={} pageIndex {} pageSize {}", teamId, pageIndex, pageSize);
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/event");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(eventService.findAllEventsByTeamPageable(teamId, pageIndex, pageSize));
    }


    @GetMapping("v1/events")
    public ResponseEntity getAllEvents(@RequestParam(value = "pageIndex", required = false, defaultValue = "0") int pageIndex,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "6") int pageSize) {
        logger.info("v1/events pageIndex {} pageSize {}", pageIndex, pageSize);
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/events");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(eventService.findAllEventsPageable(pageIndex, pageSize));
    }

}
