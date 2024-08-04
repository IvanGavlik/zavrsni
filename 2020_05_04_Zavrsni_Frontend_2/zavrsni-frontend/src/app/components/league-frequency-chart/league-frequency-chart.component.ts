import {Component, OnDestroy, OnInit} from '@angular/core';
import {ChartDataSets, ChartOptions, ChartType, LinearScale} from "chart.js";
import {Color} from "ng2-charts";
import {Subscription} from "rxjs";
import {LeagueFrequencyChartService} from "./league-frequency-chart.service";
import {LeagueFrequencyTable} from "./league-frequency-chart";

@Component({
  selector: 'app-league-frequency-chart',
  templateUrl: './league-frequency-chart.component.html',
  styleUrls: ['./league-frequency-chart.component.css']
})
export class LeagueFrequencyChartComponent implements OnInit, OnDestroy {

  // scatter chart
  scatterChartData: ChartDataSets[] = [ { data: [] } ];
  scatterChartType: ChartType = 'scatter';

  lineChartColors: Color[] = [
    {
      backgroundColor: '#6c757d',
      borderColor: '#6c757d',
      pointBackgroundColor: '#6c757d',
      pointBorderColor: '#6c757d',
      pointHoverBackgroundColor: '#6c757d',
      pointHoverBorderColor: '#6c757d'
    },
    {
      backgroundColor: '#17a2b8',
      borderColor: '#17a2b8',
      pointBackgroundColor: '#17a2b8',
      pointBorderColor: '#17a2b8',
      pointHoverBackgroundColor: '#17a2b8',
      pointHoverBorderColor: '#17a2b8'
    },
    {
      backgroundColor: '#28a745',
      borderColor: '#28a745',
      pointBackgroundColor: '#28a745',
      pointBorderColor: '#28a745',
      pointHoverBackgroundColor: '#28a745',
      pointHoverBorderColor: '#28a745'
    }
  ];

  public scatterChartOptions: (ChartOptions & { annotation: any }) = {
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
          labelString: 'frekvencija'
        }
      }]
    },
    annotation: {
      annotations: [
        {
          type: 'line',
          mode: 'vertical',
          scaleID: 'x-axis-0',
          value: 'March',
          borderColor: 'orange',
          borderWidth: 1,
          label: {
            enabled: true,
            fontColor: 'orange',
            content: 'LineAnno'
          }
        },
      ],
    },
  };


  // table data
  displayedColumns = ['goal', 'frequencyAll', 'frequencyHome', 'frequencyGuest'];
  page: LeagueFrequencyTable[] = [];
  all: Subscription;

  constructor(private leagueFrequencyChartService: LeagueFrequencyChartService) {}

  ngOnInit(): void {
    this.fetch();
  }

  fetch() {
    this.all = this.leagueFrequencyChartService.getLeagueFrequencyAll().subscribe(response => {
      this.page = [];

      const chartDataAll: any[] = [];
      response.all.forEach(el => {
        chartDataAll.push({x: el.goal, y: el.frequency});
        this.page.push({ goal: el.goal, all: el.frequency, home: 0, guest: 0 });
      });

      const chartDataHome: any[] = [];
      response.home.forEach(el => {
        chartDataHome.push({x: el.goal, y: el.frequency});
        this.page.filter(item => el.goal === item.goal)[0].home = el.frequency;
      });

      const chartDataGuest: any[] = [];
      response.guest.forEach(el => {
        chartDataGuest.push({x: el.goal, y: el.frequency});
        this.page.filter(item => el.goal === item.goal)[0].guest = el.frequency;
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
