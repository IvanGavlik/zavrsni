import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Pageable} from "../../model/pageable";
import {LigaReviewBestAttack} from "./liga-review-best-attack";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LigaReviewBestAttackService {

  constructor(private httpClient: HttpClient) {}

  getLigaReviewBestAttacks(): Observable<LigaReviewBestAttack[]> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/league-review-best-attack';
 ///   const url = 'http://localhost:8080/v3/league-review-best-attack';
    return this.httpClient.get<LigaReviewBestAttack[]>(url);
  }


}
