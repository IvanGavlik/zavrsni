import { Component, OnInit } from '@angular/core';
import {LigaReviewGoalServiceService} from "./liga-review-goal-service.service";
import {LigaReviewGoal} from "./liga-review-goal";
import {ChartType,} from "chart.js";
import {Label, MultiDataSet} from "ng2-charts";

@Component({
  selector: 'app-liga-review-goals',
  templateUrl: './liga-review-goals.component.html',
  styleUrls: ['./liga-review-goals.component.css']
})
export class LigaReviewGoalsComponent implements OnInit {

  doughnutChartLabels: Label[] = ['Domacin', 'Gost'];
  doughnutChartType: ChartType = 'doughnut';
  doughnutChartData: MultiDataSet = [[1, 1]];

  ligaReviewGoal: LigaReviewGoal = {} as LigaReviewGoal;

  constructor(private service: LigaReviewGoalServiceService) { }

  ngOnInit(): void {
    this.service.getLigaReviewGoals().subscribe(goals => {
        this.ligaReviewGoal = goals;
        this.doughnutChartData = [[ this.ligaReviewGoal.homeNumberOFGoals, this.ligaReviewGoal.guestNumberOFGoals  ]];
        this.doughnutChartLabels = [ 'Domacin: ' + this.ligaReviewGoal.homeNumberOFGoals, 'Gost: ' + this.ligaReviewGoal.guestNumberOFGoals ]
    });
  }

}
