import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {LeagueStatisticsGoalsService} from "./league-statistics-goals.service";
import {LigaReviewGoal} from "../liga-review-goals/liga-review-goal";
import {TeamReview} from "./team-review";

@Component({
  selector: 'app-league-statistics-goals',
  templateUrl: './league-statistics-goals.component.html',
  styleUrls: ['./league-statistics-goals.component.css']
})
export class LeagueStatisticsGoalsComponent implements OnInit, OnDestroy {

  displayedColumns = ['team', 'playedMatch', 'goalsScored', 'goalsScoredAverage', 'attackFactor', 'goalsConceded', 'goalsConcededAverage', 'defenseFactor'];
  page: TeamReview[] = [];
  leagueReview: LigaReviewGoal = {} as LigaReviewGoal;

  all: Subscription;

  isAll: boolean;
  isHome: boolean;
  isGuest: boolean;

  constructor(private service: LeagueStatisticsGoalsService) { }

  ngOnInit(): void {
    this.all = this.service.getTeamReview().subscribe(el => {
      this.setAllFlag();
      this.page = el;
    });

    this.service.getLeagueReview().subscribe(el => {
      this.leagueReview = el;
    });
  }

  setAllFlag() {
    this.isAll = true;
    this.isHome = false;
    this.isGuest = false;
  }

  setHomeFlag() {
    this.isAll = false;
    this.isHome = true;
    this.isGuest = false;
  }

  setGuestFlag() {
    this.isAll = false;
    this.isHome = false;
    this.isGuest = true;
  }

  ngOnDestroy(): void {
    if (this.all) {
      this.all.unsubscribe();
    }
  }

}

