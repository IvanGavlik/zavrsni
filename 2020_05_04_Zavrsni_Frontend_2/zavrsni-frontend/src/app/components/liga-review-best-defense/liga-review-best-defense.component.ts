import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {Pageable} from "../../model/pageable";
import {LigaReviewBestAttack} from "../liga-review-best-attack/liga-review-best-attack";
import {LigaReviewBestDefense} from "./liga-review-best-defense";
import {LigaReviewBestDefenseService} from "./liga-review-best-defense.service";

@Component({
  selector: 'app-liga-review-best-defense',
  templateUrl: './liga-review-best-defense.component.html',
  styleUrls: ['./liga-review-best-defense.component.css']
})
export class LigaReviewBestDefenseComponent implements OnInit, OnDestroy {

  ligaReviewBestDefenceSubscription: Subscription;
  displayedColumns = ['team', 'receivedNumberOfGoalsPerGame'];
  page: LigaReviewBestDefense[] = [];

  constructor(private service: LigaReviewBestDefenseService) { }

  ngOnInit(): void {
    this.ligaReviewBestDefenceSubscription = this.service.getLigaReviewBestDefenses().subscribe(el => this.page = el);
  }

  ngOnDestroy(): void {
    this.ligaReviewBestDefenceSubscription.unsubscribe();
  }

}
