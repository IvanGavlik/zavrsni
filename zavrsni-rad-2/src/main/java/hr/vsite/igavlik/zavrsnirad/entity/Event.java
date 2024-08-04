package hr.vsite.igavlik.zavrsnirad.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "event")
@NoArgsConstructor
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "uuid", updatable = false )
    private UUID id;

    @OneToOne()
    @JoinColumn(name = "home_team")
    private Team homeTeam;

    @Column(name = "home_team_score")
    private int homeTeamScore;

    @OneToOne()
    @JoinColumn(name = "guest_team")
    private Team guestTeam;

    @Column(name = "guest_team_score")
    private int guestTeamScore;

    @Column(name = "eventDay")
    private LocalDate eventDay;

    @Column(name = "round")
    private String round;

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event e = (Event) obj;
        if(!this.id.equals(e.id)) {
            return false;
        }
        return true;
    }
}
