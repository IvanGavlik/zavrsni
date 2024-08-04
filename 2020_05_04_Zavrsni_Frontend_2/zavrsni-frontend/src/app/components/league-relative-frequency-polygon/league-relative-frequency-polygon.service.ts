import { Injectable } from '@angular/core';
import {LeagueRelativeFrequencyChartService} from '../league-relative-frequency-chart/league-relative-frequency-chart.service';
import {Observable} from 'rxjs';
import {LeagueRelativeFrequencyChartWrapper} from '../league-relative-frequency-chart/league-relative-frequency-chart';

@Injectable({
  providedIn: 'root'
})
export class LeagueRelativeFrequencyPolygonService {

  constructor(private leagueRelativeFrequency: LeagueRelativeFrequencyChartService) { }

  getLeagueRelativeFrequencyAll(): Observable<LeagueRelativeFrequencyChartWrapper> {
    return this.leagueRelativeFrequency.getLeagueRelativeFrequencyAll();
  }
}
