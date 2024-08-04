package hr.vsite.igavlik.zavrsnirad.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "skotska1_2018_2019")
@NoArgsConstructor
@Getter
@Setter
public class FootballGame {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", updatable = false )
    private UUID id;

    @Column(name = "home_team")
    private String homeTeam;

    @Column(name = "home_team_goals")
    private int homeTeamScore;

    @Column(name = "guest_team")
    private String guestTeam;

    @Column(name = "guest_team_goals")
    private int guestTeamScore;

    @Column(name = "event_date")
    private LocalDate eventDay;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballGame that = (FootballGame) o;
        return getHomeTeamScore() == that.getHomeTeamScore() &&
                getGuestTeamScore() == that.getGuestTeamScore() &&
                Objects.equals(getHomeTeam(), that.getHomeTeam()) &&
                Objects.equals(getGuestTeam(), that.getGuestTeam()) &&
                Objects.equals(getEventDay(), that.getEventDay());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHomeTeam(), getHomeTeamScore(), getGuestTeam(), getGuestTeamScore(), getEventDay());
    }
}
