import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Event} from "../../../../model/event";
import {PageEvent} from "@angular/material/paginator";
import {EventService} from "../../../../service/event.service";
import {Pageable} from "../../../../model/pageable";

@Component({
  selector: 'app-poisson-events',
  templateUrl: './poisson-events.component.html',
  styleUrls: ['./poisson-events.component.css']
})
export class PoissonEventsComponent implements OnInit {

  /**
   * Array of the Events that will be displayed in data table
   * Event model is described in
   */
  @Input() teamId: string;

  /**
   * EventPlace is team playing at home or as guest
   */
  @Input() eventPlace: string;

  page: Pageable<Event>;
  displayedColumns = ['homeTeam', 'score', 'guestTeamScore'];

  constructor(private eventService: EventService) {
  }

  ngOnInit(): void {
    if(this.teamId) {
      this.eventService.getEventsByTeamIdFilerEventPlace(this.teamId, this.eventPlace).subscribe(
        res =>  this.page = res);
    } else {
      this.eventService.getEvents().subscribe(
        res => this.page = res);
    }
  }

  pageEvent($event: PageEvent) {
    if(this.teamId) {
      this.eventService.getEventsByTeamIdFilerEventPlace(this.teamId, this.eventPlace, $event.pageIndex, $event.pageSize).subscribe(
        res => {  this.page = res; },
      );
    } else {
      this.eventService.getEvents($event.pageIndex, $event.pageSize).subscribe(
        res => this.page = res);
    }
  }

}
