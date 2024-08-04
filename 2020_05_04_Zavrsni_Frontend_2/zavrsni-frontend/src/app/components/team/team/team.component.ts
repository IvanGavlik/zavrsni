import { Component, OnInit,  ViewChild } from '@angular/core';
import { ChartDataSets, ChartOptions } from 'chart.js';
import { Color, BaseChartDirective, Label } from 'ng2-charts';
import {Subject} from 'rxjs';
import {Event} from '../../../model/event';
import {ApiService} from '../../../api.service';

@Component({
  selector: 'app-team',
  templateUrl: './team.component.html',
  styleUrls: ['./team.component.css']
})
export class TeamComponent implements OnInit {

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
