import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {BrokerService} from "../broker/broker.service";

@Injectable({
  providedIn: 'root'
})
export class BussinesHandlerService<T> {

  constructor(bs: BrokerService<T>) {
    bs.subscribeOnTopic('test').subscribe(
      el => {

    });
  }



}
