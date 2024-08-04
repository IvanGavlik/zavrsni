package hr.vsite.igavlik.zavrsnirad.v3;

import hr.vsite.igavlik.zavrsnirad.service.v3.league.LeagueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class LeagueController {

    private static Logger logger = LoggerFactory.getLogger(LeagueController.class);

    private LeagueService leagueService;

    @Autowired
    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("v3/league-review-best-attack")
    public ResponseEntity statisticalCharacteristics() {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/league-review-best-attack");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(leagueService.reviewBestAttack());
    }

    @GetMapping("v3/league-review")
    public ResponseEntity goalSum() {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/league-review");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(leagueService.leagueReview());
    }

    @GetMapping("v3/league-review-best-defence")
    public ResponseEntity findBestDefence() {
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/league-review-best-defence");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(leagueService.findBestDefence());
    }

    @GetMapping("v3/team-review")
    public ResponseEntity teamReview() {
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/team-review");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(leagueService.teamReview());
    }

    @GetMapping("v3/league-frequency-chart")
    public ResponseEntity leagueFrequencyChartAll() {
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/league-frequency-chart");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(leagueService.leagueFrequencyChart());
    }

    @GetMapping("v3/league-relative-frequency-chart")
    public ResponseEntity leagueRelativeFrequencyChartAll() {
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/league-relative-frequency-chart");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(leagueService.leagueRelativeFrequencyChart());
    }

}
