import { Injectable } from '@angular/core';
import {Observable, of} from "rxjs";
import {Pageable} from "../../model/pageable";
import {LigaReviewBestDefense} from "./liga-review-best-defense";
import {HttpClient} from "@angular/common/http";
import {LigaReviewBestAttack} from "../liga-review-best-attack/liga-review-best-attack";

@Injectable({
  providedIn: 'root'
})
export class LigaReviewBestDefenseService {

  constructor(private httpClient: HttpClient) {}

  getLigaReviewBestDefenses(): Observable<LigaReviewBestDefense[]> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/league-review-best-defence';
  //  const url = 'http://localhost:8080/v3/league-review-best-defence';
    return this.httpClient.get<LigaReviewBestDefense[]>(url);
  }

}
