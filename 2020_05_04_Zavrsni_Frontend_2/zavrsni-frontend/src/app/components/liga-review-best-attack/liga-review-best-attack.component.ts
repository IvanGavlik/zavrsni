import {Component, OnDestroy, OnInit} from '@angular/core';
import {Pageable} from "../../model/pageable";
import {LigaReviewBestAttack} from "./liga-review-best-attack";
import {LigaReviewBestAttackService} from "./liga-review-best-attack.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-liga-review-best-attack',
  templateUrl: './liga-review-best-attack.component.html',
  styleUrls: ['./liga-review-best-attack.component.css']
})
export class LigaReviewBestAttackComponent implements OnInit, OnDestroy {

  ligaReviewBestAttackSubscription: Subscription;
  displayedColumns = ['team', 'numberOfGoalsPerGame'];
  page: LigaReviewBestAttack[] = [];

  constructor(private ligaReviewBestAttackService: LigaReviewBestAttackService) { }

  ngOnInit(): void {
    this.ligaReviewBestAttackSubscription = this.ligaReviewBestAttackService.getLigaReviewBestAttacks().subscribe(el => this.page = el);
  }

  ngOnDestroy(): void {
    this.ligaReviewBestAttackSubscription.unsubscribe();
  }

}
