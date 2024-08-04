import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LeagueFrequencyChartWrapper} from "./league-frequency-chart";

@Injectable({
  providedIn: 'root'
})
export class LeagueFrequencyChartService {

  constructor(private httpClient: HttpClient) { }

  getLeagueFrequencyAll(): Observable<LeagueFrequencyChartWrapper> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/league-frequency-chart';
//    const url = 'http://localhost:8080/v3/league-frequency-chart';
    return this.httpClient.get<LeagueFrequencyChartWrapper>(url);
  }

}
