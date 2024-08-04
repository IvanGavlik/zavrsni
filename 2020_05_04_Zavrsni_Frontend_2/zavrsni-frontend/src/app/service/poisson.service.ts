import {Injectable} from "@angular/core";
import {Observable} from 'rxjs';
import {PoissonStatistics} from "../model/PoissonStatistics";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {PoissonGuest} from "../model/poisson-guest";

@Injectable({
  providedIn: 'root'
})
export class PoissonService {

  private calculatePoissonUrl = 'https://nameless-river-96396.herokuapp.com/v1/calculatePoisson/';
  private calculatePoissonUrlWhoWillWin = 'https://nameless-river-96396.herokuapp.com/v1/calculatePoisson/whoWillWin/';
  private calculatePoissonUrlHowManyGoals = 'https://nameless-river-96396.herokuapp.com/v1/calculatePoisson/howManyGoals/';


  constructor(private http: HttpClient) {}

  calculatePoisson(homeTeamId: string, guestTeamId: string): Observable<PoissonStatistics> {
    return this.http.get<PoissonStatistics>(this.calculatePoissonUrl + '?homeTeamId=' + homeTeamId+ '&guestTeamId=' + guestTeamId);
  }

  calculateHowWillWin(homeTeamId: string, guestTeamId: string): Observable<PoissonGuest> {
    return this.http.get<PoissonGuest>(this. calculatePoissonUrlWhoWillWin + '?homeTeamId=' + homeTeamId + '&guestTeamId='+guestTeamId);
  }

  calculateHowManyGoals(teamName: string, operationID: string, goalID: string) {
    let operation = null;
    if(operationID === 'manje') {
      operation = 'LESS'
    } else if(operationID === 'jednako') {
      operation = 'SAME'
    } else if(operationID === 'više') {
      operation = 'MORE';
    }
    return this.http.get<PoissonGuest>(this. calculatePoissonUrlHowManyGoals + '?teamName=' + teamName + '&operationID='+ operation + '&goalID='+ goalID);
  }

}
