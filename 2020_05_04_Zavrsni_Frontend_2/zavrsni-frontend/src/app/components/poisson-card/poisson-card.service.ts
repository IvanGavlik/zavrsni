import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {PoissonCard} from "./poisson-card";

@Injectable({
  providedIn: 'root'
})
export class PoissonCardService {


  constructor(private httpClient: HttpClient) { }

  predictMatch(homeTeamName: string, guestTeamName: string): Observable<PoissonCard> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/match-predict';
//    const url = 'http://localhost:8080/v3/match-predict';
    return this.httpClient.get<PoissonCard>(url+'?homeTeamName='+ homeTeamName +'&guestTeamName='+guestTeamName);
  }
}
