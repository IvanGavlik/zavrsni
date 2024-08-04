import { Injectable } from '@angular/core';
import {LeagueFrequencyChartService} from "../league-frequency-chart/league-frequency-chart.service";
import {Observable} from "rxjs";
import {LeagueFrequencyChartWrapper} from "../league-frequency-chart/league-frequency-chart";

@Injectable({
  providedIn: 'root'
})
export class LeagueFrequencyPolygonService {

  constructor(private leagueFrequencyChartService: LeagueFrequencyChartService) { }

  getLeagueFrequencyAll(): Observable<LeagueFrequencyChartWrapper> {
    return this.leagueFrequencyChartService.getLeagueFrequencyAll();
  }
}
