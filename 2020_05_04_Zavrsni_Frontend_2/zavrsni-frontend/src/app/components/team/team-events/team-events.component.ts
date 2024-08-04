import { Component, OnInit } from '@angular/core';
import {Subject} from 'rxjs';
import {Event} from '../../../model/event';
import {ApiService} from '../../../api.service';

@Component({
  selector: 'app-team-events',
  templateUrl: './team-events.component.html',
  styleUrls: ['./team-events.component.css']
})
export class TeamEventsComponent implements OnInit {

  // data table
  dtOptions: DataTables.Settings = {};
  ddtTrigger: Subject<any> = new Subject();
  events: Event[] = [];

  constructor(private api: ApiService) { }

  ngOnInit() {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 10,
      lengthChange: false,
      searching: false
    };

    this.api.getEvents()
      .subscribe(resp => {
        for (const data of resp.body) {
          this.events.push(data);
        }
        // Calling the DT trigger to manually render the table
        this.ddtTrigger.next();
      });
  }
}
