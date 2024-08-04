package hr.vsite.igavlik.zavrsnirad.v3;

import hr.vsite.igavlik.zavrsnirad.service.v3.simulation.Simulation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SimulationController {

    private static Logger logger = LoggerFactory.getLogger(SimulationController.class);

    private Simulation simulation;

    @Autowired
    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    @GetMapping("v3/simulation")
    public ResponseEntity simulation() {

        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v3/simulation");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body(simulation.simulate());
    }
}
