package hr.vsite.igavlik.zavrsnirad.v2;

import hr.vsite.igavlik.zavrsnirad.service.v3.FootballGameService;
import hr.vsite.igavlik.zavrsnirad.service.PoissonFilterGuestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class PoissonFilterController {

    private static Logger logger = LoggerFactory.getLogger(PoissonFilterController.class);

    private PoissonFilterGuestService poissonFilterGuestService;
    private FootballGameService footballGameService;

    @Autowired
    public PoissonFilterController(PoissonFilterGuestService poissonFilterGuestService, FootballGameService footballGameService) {
        this.poissonFilterGuestService = poissonFilterGuestService;
        this.footballGameService = footballGameService;
    }

    // used to test v3
    @GetMapping("v2/statisticalCharacteristics")
    public ResponseEntity statisticalCharacteristics() {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v2/event");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body("ok");
    }


}
