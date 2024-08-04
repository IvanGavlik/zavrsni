import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BrokerService<T> {

  constructor() { }

  publishOnTopic(payload: T): void {}

  subscribeOnTopic(topicName: string): Observable<T> {
    return null;
  }

}
