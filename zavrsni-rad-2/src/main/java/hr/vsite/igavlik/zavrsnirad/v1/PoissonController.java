package hr.vsite.igavlik.zavrsnirad.v1;

import hr.vsite.igavlik.zavrsnirad.service.PoissonService;
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
public class PoissonController {

    private static Logger logger = LoggerFactory.getLogger(PoissonController.class);

    private PoissonService poissonService;

    @Autowired
    public PoissonController(PoissonService poissonService) {
        this.poissonService = poissonService;
    }

    @GetMapping("v1/calculatePoisson")
    public ResponseEntity calculatePoisson(@RequestParam String homeTeamId, @RequestParam String guestTeamId) {
        logger.info("v1/calculatePoisson {} vs {}", homeTeamId, guestTeamId);
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/calculatePoisson");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(poissonService.calculatePoisson(homeTeamId, guestTeamId));
    }

    @GetMapping("v1/calculatePoisson/whoWillWin")
    public ResponseEntity whoWillWin(@RequestParam String homeTeamId, @RequestParam String guestTeamId) {
        logger.info("v1/calculatePoisson whoWillWin {} vs {}", homeTeamId, guestTeamId);

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/calculatePoisson/whoWillWin");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(poissonService.whoWillWin(homeTeamId, guestTeamId));
    }

    @GetMapping("v1/calculatePoisson/howManyGoals")
    public ResponseEntity calculateHowManyGoals(@RequestParam String teamName, @RequestParam String operationID,  @RequestParam String goalID) {
        logger.info("v1/calculatePoisson howManyGoals {}, {}, {}", teamName, operationID, goalID);

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/calculatePoisson/howManyGoals");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        PoissonService.Operation operation = null;
        try {
            operation = PoissonService.Operation.valueOf(operationID);
        } catch (Exception ex) {
            throw new RuntimeException("goalID", ex);
        }

        Integer howManyGoals = 0;
        try {
            howManyGoals = Integer.parseInt(goalID);
        } catch (Exception ex) {
            throw new RuntimeException("goalID", ex);
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(poissonService.calculateHowManyGoals(teamName, operation, howManyGoals));
    }
}
