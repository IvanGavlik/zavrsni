import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {Spinner} from "../model/spinner";

@Injectable({
  providedIn: 'root'
})
export class SpinnerService {

  private spinner: Subject<Spinner> = new Subject<Spinner>();

  constructor() { }

  showSpinner(msg: string = 'Učitavam...'): void {
    this.spinner.next({display: true, msg: msg});
  }

  hideSpinner(msg: string = 'Učitavam...'): void {
    this.spinner.next({display: false, msg: msg});
  }

  getSpinner(): Observable<Spinner> {
    return this.spinner;
  }

  static handleError(spinner: SpinnerService, error) {
    spinner.hideSpinner(), alert('Nešto je pošlo po zlu! ' + error)
  }

}
