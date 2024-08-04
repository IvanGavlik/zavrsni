import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {PoissonCardService} from './poisson-card.service';
import {Subscription} from 'rxjs';
import {PoissonCard} from './poisson-card';
import {Label, MultiDataSet} from 'ng2-charts';
import {ChartType} from 'chart.js';
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatchDialogComponent} from "./match-dialog/match-dialog.component";
import {dashCaseToCamelCase} from "@angular/compiler/src/util";
import {Probability} from "./probability";

@Component({
  selector: 'app-poisson-card',
  templateUrl: './poisson-card.component.html',
  styleUrls: ['./poisson-card.component.css']
})
export class PoissonCardComponent implements OnInit, OnDestroy {

  @Input() formOutside: string;
  @Input() poissonCard: PoissonCard = {} as PoissonCard;
  probability: Probability = {} as Probability;

  doughnutChartType: ChartType = 'doughnut';
  doughnutChartLabels: Label[] = [];
  doughnutChartData: MultiDataSet = [[]];


  private all: Subscription;

  selectedTeam: boolean;

  constructor(private poissonCardService: PoissonCardService, private dialog: MatDialog) { }

  ngOnInit(): void {
    if (this.formOutside !== 'true') {
      this.createMatch();
    } else {
      this.fillData();
    }
  }

  ngOnDestroy(): void {
    if (this.all) {
      this.all.unsubscribe();
    }
  }

  createMatch() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(MatchDialogComponent, dialogConfig);

    dialogRef.afterOpened().subscribe(data => {
      this.selectedTeam = false;
    });

    dialogRef.afterClosed().subscribe(data => {
      this.all = this.poissonCardService.predictMatch(data.home, data.guess).subscribe(el => {
        this.poissonCard = el;
        this.probability = {
          homeTeamProbabilityOf0: this.poissonCard.homeTeamProbabilityOf0,
          homeTeamProbabilityOf1: this.poissonCard.homeTeamProbabilityOf1,
          homeTeamProbabilityOf2: this.poissonCard.homeTeamProbabilityOf2,
          homeTeamProbabilityOf3: this.poissonCard.homeTeamProbabilityOf3,
          homeTeamProbabilityOf4: this.poissonCard.homeTeamProbabilityOf4,
          homeTeamProbabilityOf5: this.poissonCard.homeTeamProbabilityOf5,

          guestTeamProbabilityOf0: this.poissonCard.guestTeamProbabilityOf0,
          guestTeamProbabilityOf1: this.poissonCard.guestTeamProbabilityOf1,
          guestTeamProbabilityOf2: this.poissonCard.guestTeamProbabilityOf2,
          guestTeamProbabilityOf3: this.poissonCard.guestTeamProbabilityOf3,
          guestTeamProbabilityOf4: this.poissonCard.guestTeamProbabilityOf4,
          guestTeamProbabilityOf5: this.poissonCard.guestTeamProbabilityOf5

        } as Probability;
        this.fillData();
      });
    });
  }

  fillData() {
    this.doughnutChartLabels = [];
    this.doughnutChartLabels.push(this.poissonCard.homeTeamName);
    this.doughnutChartLabels.push(this.poissonCard.guestTeamName);
    this.doughnutChartData = [[this.poissonCard.homeTeamLambda, this.poissonCard.guestTeamLambda]];
    this.selectedTeam = true;
  }

}
