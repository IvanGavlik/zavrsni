package hr.vsite.igavlik.zavrsnirad.dto;

import hr.vsite.igavlik.zavrsnirad.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class EventDto {
    private String homeTeamName;
    private int homeTeamScore;
    private String guestTeamName;
    private int guestTeamScore;
    private LocalDate eventDay;
    private String round;

    public EventDto(Event event) {
        this.homeTeamName = event.getHomeTeam().getName();
        this.homeTeamScore = event.getHomeTeamScore();
        this.guestTeamName = event.getGuestTeam().getName();
        this.guestTeamScore = event.getGuestTeamScore();
        this.eventDay = event.getEventDay();
        this.round = event.getRound();
    }

    public EventDto() {}
}
