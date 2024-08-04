import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType} from "chart.js";
import {BaseChartDirective, Color, Label} from "ng2-charts";
import * as pluginAnnotations from 'chartjs-plugin-annotation';
import {Subscription} from "rxjs";
import {LeagueRelativeFrequencyChartService} from "../league-relative-frequency-chart/league-relative-frequency-chart.service";
import {LeagueRelativeFrequencyPolygonService} from "./league-relative-frequency-polygon.service";
import {LeagueRelativeFrequencyTable} from "../league-relative-frequency-chart/league-relative-frequency-chart";


@Component({
  selector: 'app-league-relative-frequency-polygon',
  templateUrl: './league-relative-frequency-polygon.component.html',
  styleUrls: ['./league-relative-frequency-polygon.component.css']
})
export class LeagueRelativeFrequencyPolygonComponent implements OnInit, OnDestroy {

  lineChartData: ChartDataSets[] = [];
  lineChartLabels: Label[] = [];
  lineChartColors: Color[] = [
    { // blue
      backgroundColor: 'rgba(0,0,0,0)',
      borderColor: '#6c757d',
      pointBackgroundColor: '#6c757d',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgb(65,105,225,0.8)'
    },
    { // lime green
      backgroundColor: 'rgba(0,0,0,0)',
      borderColor: '#17a2b8',
      pointBackgroundColor: '#17a2b8',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgb(50,205,50,0.8)'
    },
    { // grey
      backgroundColor: 'rgba(0,0,0,0)',
      borderColor: '#28a745',
      pointBackgroundColor: '#28a745',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },
  ];


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

  all: Subscription;
  displayedColumns = ['goal', 'frequencyAll', 'frequencyHome', 'frequencyGuest'];
  page: LeagueRelativeFrequencyTable[] = [];

  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;

  constructor(private service: LeagueRelativeFrequencyPolygonService) { }

  ngOnInit(): void {
    this.all = this.service.getLeagueRelativeFrequencyAll().subscribe(response => {
      this.page = [];

      const chartDataAll: number[] = [];
      response.all.forEach(el => {
        chartDataAll.push(el.relativeFrequency);
        this.page.push({ goal: el.goal, all: el.relativeFrequency, home: 0, guest: 0 });
      });

      const chartDataHome: number[] = [];
      response.home.forEach(el => {
        chartDataHome.push(el.relativeFrequency);
        this.page.filter(item => el.goal === item.goal)[0].home = el.relativeFrequency;
      });

      const chartDataGuest: number[] = [];
      response.guest.forEach(el => {
        chartDataGuest.push(el.relativeFrequency);
        this.page.filter(item => el.goal === item.goal)[0].guest = el.relativeFrequency;
      });

      this.lineChartData = [
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
      this.lineChartLabels = ['0', '1', '2', '3', '4', '5'];
    });
  }

  ngOnDestroy(): void {
    if (this.all) {
      this.all.unsubscribe();
    }
  }

}
