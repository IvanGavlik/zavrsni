import {Component, OnDestroy, OnInit} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from "chart.js";
import {Color, Label} from "ng2-charts";
import {Subscription} from "rxjs";
import {LeagueRelativeFrequencyChartService} from "./league-relative-frequency-chart.service";
import {LeagueRelativeFrequencyTable} from "./league-relative-frequency-chart";

@Component({
  selector: 'app-league-relative-frequency-chart',
  templateUrl: './league-relative-frequency-chart.component.html',
  styleUrls: ['./league-relative-frequency-chart.component.css']
})
export class LeagueRelativeFrequencyChartComponent implements OnInit, OnDestroy {

  // scatter
  public scatterChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      xAxes: [
        {
          ticks: {
            max: 5,
            min: 0,
            stepSize: 1
          },
          scaleLabel: {
            display: true,
            labelString: 'broj golova'
          }
        }],
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'relativna frekvencija'
        }
      }]
    },
  };
  public scatterChartLabels: Label[] = ['Eating'];

  scatterChartData: ChartDataSets[] = [ { data: [] } ];
  scatterChartType: ChartType = 'scatter';

  lineChartColors: Color[] = [
    { // blue
      backgroundColor: '#6c757d',
      borderColor: '#6c757d',
      pointBackgroundColor: '#6c757d',
      pointBorderColor: '#6c757d',
      pointHoverBackgroundColor: '#6c757d',
      pointHoverBorderColor: '#6c757d'
    },
    { // blue
      backgroundColor: '#17a2b8',
      borderColor: '#17a2b8',
      pointBackgroundColor: '#17a2b8',
      pointBorderColor: '#17a2b8',
      pointHoverBackgroundColor: '#17a2b8',
      pointHoverBorderColor: '#17a2b8'
    },
    { // blue
      backgroundColor: '#28a745',
      borderColor: '#28a745',
      pointBackgroundColor: '#28a745',
      pointBorderColor: '#28a745',
      pointHoverBackgroundColor: '#28a745',
      pointHoverBorderColor: '#28a745'
    }
  ];

  all: Subscription;
  displayedColumns = ['goal', 'frequencyAll', 'frequencyHome', 'frequencyGuest'];
  page: LeagueRelativeFrequencyTable[] = [];

  constructor(private service: LeagueRelativeFrequencyChartService) { }

  ngOnInit(): void {
    this.fetch();
  }

  fetch() {
    this.all = this.service.getLeagueRelativeFrequencyAll().subscribe(response => {
      this.page = [];

      const chartDataAll: any[] = [];
      response.all.forEach(el => {
        chartDataAll.push({x: el.goal, y: el.relativeFrequency});
        this.page.push({ goal: el.goal, all: el.relativeFrequency, home: 0, guest: 0 });
      });

      const chartDataHome: any[] = [];
      response.home.forEach(el => {
        chartDataHome.push({x: el.goal, y: el.relativeFrequency});
        this.page.filter(item => el.goal === item.goal)[0].home = el.relativeFrequency;
      });

      const chartDataGuest: any[] = [];
      response.guest.forEach(el => {
        chartDataGuest.push({x: el.goal, y: el.relativeFrequency});
        this.page.filter(item => el.goal === item.goal)[0].guest = el.relativeFrequency;
      });

      this.scatterChartData = [
        {
          data: chartDataAll,
          label: 'Sve',
        },
        {
          data: chartDataHome,
          label: 'Domaće',
        },
        {
          data: chartDataGuest,
          label: 'Gosti',
        }
        ];

    });
  }

  ngOnDestroy(): void {
    if (this.all) {
      this.all.unsubscribe();
    }
  }
}
