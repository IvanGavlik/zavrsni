import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ChartDataSets, ChartOptions} from "chart.js";
import {BaseChartDirective, Color, Label} from "ng2-charts";
import {StatisticalFeatureChart, StatisticalFeatureTableItem} from "../../../../model/PoissonStatistics";


@Component({
  selector: 'app-poisson-frequency-chart',
  templateUrl: './poisson-frequency-chart.component.html',
  styleUrls: ['./poisson-frequency-chart.component.css']
})
export class PoissonFrequencyChartComponent implements OnInit, OnChanges {
  // graf
  // TODO not from input, retrive from service (StatisticalCharacteristics->chart->homeTeam...)
  @Input() statisticalFeaturesChartHomeTeam: StatisticalFeatureChart;
  @Input() statisticalFeaturesChartGuestTeam: StatisticalFeatureChart;
  @Input() statisticalFeaturesChartLeague: StatisticalFeatureChart;


  public lineChartData: ChartDataSets[] = [];
  public lineChartLabels: Label[] = [];
  public lineChartOptions: (ChartOptions & { annotation: any }) = {
    responsive: true,
    scales: {
      yAxes: [{
        ticks: {
          max: 5,
          min: 0,
          stepSize: 1
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
  public lineChartColors: Color[] = [
    { // blue
      backgroundColor: 'rgba(0,0,0,0)',
      borderColor: 'royalblue',
      pointBackgroundColor: 'rgb(65,105,225,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgb(65,105,225,0.8)'
    },
    { // lime green
      backgroundColor: 'rgba(0,0,0,0)',
      borderColor: 'rgb(50,205,50,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgb(50,205,50,0.8)'
    },
    { // grey
      backgroundColor: 'rgba(0,0,0,0)',
      borderColor: 'rgba(148,159,177,1)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(148,159,177,0.8)'
    },

  ];
  public lineChartLegend = true;
  public lineChartType = 'line';

  @ViewChild(BaseChartDirective, { static: true }) chart: BaseChartDirective;

  //TODO not @Input() go on service, retrive one object StatisticalCharacteristics -> tableItems
  @Input() statisticalFeaturesTableHomeTeam: StatisticalFeatureTableItem[];
  @Input() statisticalFeaturesTableGuestTeam: StatisticalFeatureTableItem[];

  constructor() {
    this.statisticalFeaturesChartLeague = this.test;
  }

  ngOnInit(): void {
    this.fetchData();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.fetchData();
  }


  fetchData(): void {
    let labels: string[] = [];
    let homeTeamData: number[] = [];
    this.statisticalFeaturesChartHomeTeam.data.forEach(el => { homeTeamData.push(el.value); labels.push(el.label + '.');} );

    let guestTeamData: number[] = [];
    this.statisticalFeaturesChartGuestTeam.data.forEach(el => guestTeamData.push(el.value));

    let leagueData: number[] = [];
    this.statisticalFeaturesChartLeague.data.forEach(el => leagueData.push(el.value));

    this.lineChartData = [
      {data: homeTeamData, label: this.statisticalFeaturesChartHomeTeam.title},
      {data: guestTeamData, label: this.statisticalFeaturesChartGuestTeam.title},
      {data: leagueData, label: this.statisticalFeaturesChartLeague.title}
    ];
    this.lineChartLabels = labels;
  }

  test: StatisticalFeatureChart = {
    title : 'test', data :  [ { label: 1, value: 1 } ]
  }

}
