package hr.vsite.igavlik.zavrsnirad.v1;

import hr.vsite.igavlik.zavrsnirad.service.TeamService;
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
public class TeamController {

    private static Logger logger = LoggerFactory.getLogger(TeamController.class);
    private TeamService teamService;

    
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("v1/team/all")
    public ResponseEntity getTeam() {
        logger.info("v1/team/all");
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/team/all");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body( teamService.getTeams()) ;

    }

    @GetMapping("v1/team")
    public ResponseEntity getTeam(@RequestParam String team) {
        logger.info("v1/team {}", team);
        URI locationUri = null;
        try {
            locationUri = new URI("https://nameless-river-96396.herokuapp.com/v1/team");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity
                .created(locationUri)
                .header("Access-Control-Allow-Origin", "*")
                .body( teamService.getTeamInfo(team)) ;
    }

}
