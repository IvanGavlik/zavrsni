package hr.vsite.igavlik.zavrsnirad.service;

import hr.vsite.igavlik.zavrsnirad.dto.v2.StatisticalCharacteristics;

public interface PoissonFilterGuestService {

    StatisticalCharacteristics calculateStatisticalCharacteristics(String homeTeamId, String guestTeamId);

}
