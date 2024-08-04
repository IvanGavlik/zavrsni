import { Injectable } from '@angular/core';
import {PoissonCardDetails} from './poisson-card-details';
import {Observable, of} from 'rxjs';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PoissonCardDetailsService {

  constructor(private httpClient: HttpClient) {}

  getPoissonCardDetails(homeTeam: string, guessTeam: string): Observable<PoissonCardDetails> {
        const url = 'https://nameless-river-96396.herokuapp.com/v3/match-predict-detail';
//    const url = 'http://localhost:8080/v3/match-predict-detail';
    return this.httpClient.get<PoissonCardDetails>(url+'?homeTeamName='+ homeTeam +'&guestTeamName='+guessTeam);
  }
}
