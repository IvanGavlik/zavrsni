import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LeagueRelativeFrequencyChartWrapper} from "./league-relative-frequency-chart";

@Injectable({
  providedIn: 'root'
})
export class LeagueRelativeFrequencyChartService {

  constructor(private httpClient: HttpClient) { }

  getLeagueRelativeFrequencyAll(): Observable<LeagueRelativeFrequencyChartWrapper> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/league-relative-frequency-chart';
//    const url = 'http://localhost:8080/v3/league-relative-frequency-chart';
    return this.httpClient.get<LeagueRelativeFrequencyChartWrapper>(url);
  }
}
