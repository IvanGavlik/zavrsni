import {Injectable} from "@angular/core";
import {Event} from "../model/event";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Pageable} from "../model/pageable";


@Injectable({
  providedIn: 'root'
})
export class EventService {

  private eventsByTeamUrl = 'https://nameless-river-96396.herokuapp.com/v1/event/?teamId=';
  private eventsByTeamFilterEventPlaceUrl = 'https://nameless-river-96396.herokuapp.com/v2/event/?teamId=';
  private eventsAll = 'https://nameless-river-96396.herokuapp.com/v1/events/';


  constructor(private http: HttpClient) {}

  // TODO at some point this method must be deleted and not used
  getEventsByTeamId(teamId: string, index: number = 0, size: number= 6): Observable<Pageable<Event>> {
    return this.http.get<Pageable<Event>>(this.eventsByTeamUrl + teamId + '&pageIndex=' + index + '&pageSize='+ size);
  }

  getEventsByTeamIdFilerEventPlace(teamId: string, eventPlace: string, index: number = 0, size: number= 6): Observable<Pageable<Event>> {
    return this.http.get<Pageable<Event>>(this.eventsByTeamFilterEventPlaceUrl + teamId + '&eventPlace=' + eventPlace +
      '&pageIndex=' + index + '&pageSize='+ size);
  }

  getEvents(index: number = 0, size: number= 6): Observable<Pageable<Event>> {
    return this.http.get<Pageable<Event>>(this.eventsAll + '?pageIndex=' + index + '&pageSize='+ size);
  }
}
