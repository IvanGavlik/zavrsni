import {Injectable} from '@angular/core';
import {Team} from '../model/Team';
import {Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

  private teamAllUrl = 'https://nameless-river-96396.herokuapp.com/v1/team/all';

  constructor(private http: HttpClient) {}

  getTeams(): Observable<Team[]> {
    return this.http.get<Team[]>(this.teamAllUrl);
  }


}
