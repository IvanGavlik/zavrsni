package hr.vsite.igavlik.zavrsnirad.dto.v3;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TeamReview {
    private String name;

    private int allNumberOFGoals;
    private int allNumberOFGames;
    private double allAverageGoals;
    private int homeNumberOFGoals;
    private int homeNumberOFGames;
    private double homeAverageGoals;
    private int guestNumberOFGoals;
    private int guestNumberOFGames;
    private double guestAverageGoals;

    private int receivedAllNumberOFGoals;
    private int receivedAllNumberOFGames;
    private double receivedAllAverageGoals;
    private int receivedHomeNumberOFGoals;
    private int receivedHomeNumberOFGames;
    private double receivedHomeAverageGoals;
    private int receivedGuestNumberOFGoals;
    private int receivedGuestNumberOFGames;
    private double receivedGuestAverageGoals;

    // attack/defense factors
    private double asHomeAttackFactor;
    private double asHomeDefensekFactor;
    private double asGuestAttackFactor;
    private double asGuestDefensekFactor;
    private double allAttackFactor;
    private double allDefensekFactor;

}
