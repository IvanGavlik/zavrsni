package hr.vsite.igavlik.zavrsnirad.v3;

import hr.vsite.igavlik.zavrsnirad.service.v3.FootballGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

@RestController
public class AssetController {

    private static Logger logger = LoggerFactory.getLogger(AssetController.class);

    private FootballGameService footballGameService;

    @Autowired
    public AssetController(FootballGameService footballGameService) { this.footballGameService = footballGameService; }

    @GetMapping("v3/asset-all")
    public ResponseEntity statisticalCharacteristics() {
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/asset-all");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(footballGameService.getFootballGames().collect(Collectors.toList()));
    }
}
