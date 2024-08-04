import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {LigaReviewGoal} from "../liga-review-goals/liga-review-goal";
import {HttpClient} from "@angular/common/http";
import {PoissonCard} from "../poisson-card/poisson-card";

@Injectable({
  providedIn: 'root'
})
export class SimulationService {

  constructor(private httpClient: HttpClient) { }

  getSimulation(): Observable<PoissonCard[]> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/simulation';
   //  const url = 'http://localhost:8080/v3/simulation';
    return this.httpClient.get<PoissonCard[]>(url);
  }
}
