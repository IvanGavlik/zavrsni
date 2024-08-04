package hr.vsite.igavlik.zavrsnirad.service.v3.match;

import hr.vsite.igavlik.zavrsnirad.dto.v3.PoissonCard;
import hr.vsite.igavlik.zavrsnirad.dto.v3.PoissonCardDetails;

public interface MatchPredictService {

    PoissonCard getPoissonCard(String homeTeamName, String guestTeamName);

    PoissonCardDetails getPoissonCardDetails(String homeTeamName, String guestTeamName);
}
