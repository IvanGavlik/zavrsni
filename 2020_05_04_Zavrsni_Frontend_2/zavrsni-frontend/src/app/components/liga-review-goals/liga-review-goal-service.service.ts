import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {LigaReviewGoal} from "./liga-review-goal";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LigaReviewGoalServiceService {

  constructor(private httpClient: HttpClient) {}

  getLigaReviewGoals(): Observable<LigaReviewGoal> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/league-review';
   // const url = 'http://localhost:8080/v3/league-review';
    return this.httpClient.get<LigaReviewGoal>(url);
  }
}
