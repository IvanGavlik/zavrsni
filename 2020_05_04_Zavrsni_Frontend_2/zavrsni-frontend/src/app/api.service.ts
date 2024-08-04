import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse  } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event} from './model/event';

//TODO DELTE ApiService

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  getEvents(): Observable<HttpResponse<Event[]>> {
    return this.http.get<Event[]>('https://nameless-river-96396.herokuapp.com/v1/event',
      { observe: 'response' });
  }
}
