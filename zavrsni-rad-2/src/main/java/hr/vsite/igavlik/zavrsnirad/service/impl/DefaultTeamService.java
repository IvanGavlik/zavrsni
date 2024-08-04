package hr.vsite.igavlik.zavrsnirad.service.impl;

import hr.vsite.igavlik.zavrsnirad.dto.TeamDto;
import hr.vsite.igavlik.zavrsnirad.entity.Team;
import hr.vsite.igavlik.zavrsnirad.repository.TeamRepository;
import hr.vsite.igavlik.zavrsnirad.service.TeamService;
import hr.vsite.igavlik.zavrsnirad.v1.TeamController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DefaultTeamService implements TeamService {

    private static Logger logger = LoggerFactory.getLogger(TeamController.class);
    private TeamRepository teamRepository;

    @Autowired
    public DefaultTeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public Optional<Team> getTeamByName(String name) {
        return teamRepository.getByName(name);
    }

    @Override
    public Optional<Team> getTeamById(String id) {
        return teamRepository.getAllById(UUID.fromString(id));
    }

    @Override
    public List<TeamDto> getTeams() {
        return teamRepository.findAll().stream()
                .sorted(Comparator.comparing(Team::getName))
                .map(el -> new TeamDto(el.getId().toString(), el.getName()))
                .collect(Collectors.toList());
    }

    //TODO write tests
    /**
     * @param teamName if null or empty returns 0
     * @return team info like how much team is scored
     */
    @Override
    @Transactional
    public TeamDto getTeamInfo(final String teamName) {
        logger.info("getTeamInfo {}", teamName);
        TeamDto team = new TeamDto(null,null);
        return team;
    }

}
