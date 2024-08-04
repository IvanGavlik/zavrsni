package hr.vsite.igavlik.zavrsnirad.v3;

import hr.vsite.igavlik.zavrsnirad.service.v3.match.MatchPredictService;
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
public class PoissonMatchPredictController {

    private static Logger logger = LoggerFactory.getLogger(PoissonMatchPredictController.class);

    private MatchPredictService  matchPredictService;

    @Autowired
    public PoissonMatchPredictController(MatchPredictService matchPredictService) {
        this.matchPredictService = matchPredictService;
    }

    @GetMapping("v3/match-predict")
    public ResponseEntity matchPredict(@RequestParam() String homeTeamName, @RequestParam() String guestTeamName) {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/match-predict");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(matchPredictService.getPoissonCard(homeTeamName, guestTeamName));
    }

    @GetMapping("v3/match-predict-detail")
    public ResponseEntity matchPredictDetail(@RequestParam() String homeTeamName, @RequestParam() String guestTeamName) {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/match-predict-detail");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(matchPredictService.getPoissonCardDetails(homeTeamName, guestTeamName));
    }


}
