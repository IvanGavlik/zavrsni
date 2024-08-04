import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {PoissonCardDetails} from './poisson-card-details';
import {PoissonCardDetailsService} from './poisson-card-details.service';
import {Subscription} from 'rxjs';
import {Probability} from "../probability";

@Component({
  selector: 'app-poisson-card-details',
  templateUrl: './poisson-card-details.component.html',
  styleUrls: ['./poisson-card-details.component.css']
})
export class PoissonCardDetailsComponent implements OnDestroy {

  @Input() homeTeamName: string;
  @Input() guessTeamName: string;
  @Input() probability: Probability;

  showDetail: boolean;

  poissonCardDetails: PoissonCardDetails = {} as PoissonCardDetails;
  all: Subscription;

  constructor(private service: PoissonCardDetailsService) { }

  loadDetails() {
    this.showDetail = !this.showDetail;
    if (this.showDetail) {
      this.all = this.service.getPoissonCardDetails(this.homeTeamName, this.guessTeamName).subscribe(el => {
          this.poissonCardDetails = el;
      });
    }
  }

  getHomeTeamAttach() {
    return this.poissonCardDetails.homeTeamHomeNumberOfGoalsAverage  /  this.poissonCardDetails.leagueHomeNumberOfGoalsAverage;
  }

  getGuessAttach() {
    return this.poissonCardDetails.guestTeamGuestNumberOfGoalsAverage / this.poissonCardDetails.leagueGuestNumberOfGoalsAverage
  }

  getHomeDefense() {
    return this.poissonCardDetails.homeTeamHomeNumberOfGoalsReceivedAverage  / this.poissonCardDetails.leagueGuestNumberOfGoalsAverage;
  }

  getGuessDefence() {
    return this.poissonCardDetails.guestTeamGuestNumberOfGoalsReceivedAverage  / this.poissonCardDetails.leagueHomeNumberOfGoalsAverage;
  }

  ngOnDestroy(): void {
    if (this.all) {
      this.all.unsubscribe();
    }
  }

  getLambdaHome() {
    return this.getHomeTeamAttach()  * this.getGuessDefence() * this.poissonCardDetails.leagueHomeNumberOfGoalsAverage;
  }

  getLambdaGuess() {
    return this.getGuessAttach() * this.getHomeDefense() * this.poissonCardDetails.leagueGuestNumberOfGoalsAverage;
  }
}
