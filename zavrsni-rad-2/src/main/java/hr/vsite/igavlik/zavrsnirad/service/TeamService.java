package hr.vsite.igavlik.zavrsnirad.service;

import hr.vsite.igavlik.zavrsnirad.dto.TeamDto;
import hr.vsite.igavlik.zavrsnirad.entity.Team;

import java.util.List;
import java.util.Optional;

public interface TeamService {

    TeamDto getTeamInfo(String teamName);

    List<TeamDto> getTeams();

    Optional<Team> getTeamByName(String name);

    Optional<Team> getTeamById(String id);

}
