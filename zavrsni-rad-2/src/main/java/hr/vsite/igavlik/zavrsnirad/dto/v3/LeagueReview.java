package hr.vsite.igavlik.zavrsnirad.dto.v3;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeagueReview {
    private int allNumberOFGoals;
    private int allNumberOFGames;
    private double allAverageGoals;
    private int homeNumberOFGoals;
    private int homeNumberOFGames;
    private double homeAverageGoals;
    private int guestNumberOFGoals;
    private int guestNumberOFGames;
    private double guestAverageGoals;
}
