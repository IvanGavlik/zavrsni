import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {LigaReviewGoalServiceService} from "../liga-review-goals/liga-review-goal-service.service";
import {LigaReviewGoal} from "../liga-review-goals/liga-review-goal";
import {TeamReview} from "./team-review";

@Injectable({
  providedIn: 'root'
})
export class LeagueStatisticsGoalsService {

  constructor(private httpClient: HttpClient, private ligaReviewGoalServiceService: LigaReviewGoalServiceService) {}

  getTeamReview(): Observable<TeamReview[]> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/team-review';
//    const url = 'http://localhost:8080/v3/team-review';
    return this.httpClient.get<TeamReview[]>(url);
  }

  getLeagueReview():  Observable<LigaReviewGoal> {
    return this.ligaReviewGoalServiceService.getLigaReviewGoals();
  }

}
