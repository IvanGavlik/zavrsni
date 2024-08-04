import {Component, Input, OnInit} from '@angular/core';
import {MatSelectChange} from "@angular/material/select/select";
import {Team} from "../../../../model/Team";
import {PoissonGuest} from "../../../../model/poisson-guest";
import {PoissonService} from "../../../../service/poisson.service";

@Component({
  selector: 'app-poisson-guess',
  templateUrl: './poisson-guess.component.html',
  styleUrls: ['./poisson-guess.component.css']
})
export class PoissonGuessComponent implements OnInit {

  @Input() teams: Team[];

  // how will win HWW
  poissonGuessHWW: PoissonGuest;

  //how many goals HMG
  teamIDHMG: string;
  operations: string[];
  operationID: string;
  goals: string[];
  goalID: string;
  isHowManyGoalsCalculated: boolean;
  poissonGuessHMG: PoissonGuest;

  constructor(private poissonService: PoissonService) { }

  ngOnInit(): void {
    this.operations = [ 'manje', 'jednako', 'više' ];
    this.goals = ['0', '1', '2', '3', '4', '5'];  }

  whoWillWin() {
    this.poissonService.calculateHowWillWin(this.teams[0].id, this.teams[1].id).subscribe(
      res => this.poissonGuessHWW = res
    );
  }

  selectTeamHMG(change: MatSelectChange) {
    if(this.isHowManyGoalsCalculated) {
      this.howManyGoalsCalculationDelete();
      this.teamIDHMG = change.value;
    }
  }

  selectOperation(change: MatSelectChange) {
    if(this.isHowManyGoalsCalculated) {
      this.howManyGoalsCalculationDelete();
      this.operationID = change.value;
    }
  }

  selectGoal(change: MatSelectChange) {
    if(this.isHowManyGoalsCalculated) {
      this.howManyGoalsCalculationDelete();
      this.goalID = change.value;
    }
  }

  howManyGoalsCalculate() {
    if(this.teamIDHMG && this.operationID && this.goalID) {
      this.poissonService.calculateHowManyGoals(this.teamIDHMG, this.operationID, this.goalID).subscribe(
        res => this.poissonGuessHMG = res
      );
      this.isHowManyGoalsCalculated = true;
    }
  }

  howManyGoalsCalculationDelete() {
    this.operationID = '';
    this.goalID = '';
    this.teamIDHMG = '';
    this.isHowManyGoalsCalculated = false;
  }

}
