import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {StatisticalFeatureTableItem} from "../../../../../model/PoissonStatistics";

@Component({
  selector: 'app-poisson-frequency-chart-table',
  templateUrl: './poisson-frequency-chart-table.component.html',
  styleUrls: ['./poisson-frequency-chart-table.component.css']
})
export class PoissonFrequencyChartTableComponent implements OnInit, OnChanges {

  displayedColumns = ['goalId', 'frequency','relativeFrequency', 'arithmeticMiddle', 'dispersion'];
  @Input() statisticalFeaturesTable: StatisticalFeatureTableItem[];
  @Input() title: string;

  frequencySum: number = 0;
  relativeFrequencySum: number = 0;
  arithmeticMiddleSum: number = 0;
  dispersionSum: number = 0;
  deviationSum: number = 0;

  constructor() { }

  ngOnInit(): void {
    this.fetchData();
  }
  fetchData(): void {
    this.frequencySum = 0;
    this.relativeFrequencySum = 0;
    this.arithmeticMiddleSum = 0;
    this.dispersionSum = 0;
    this.deviationSum = 0;

    this.statisticalFeaturesTable.forEach(
      el => {
        this.frequencySum += el.frequency;
        this.relativeFrequencySum += el.relativeFrequency;
        this.arithmeticMiddleSum += el.arithmeticMiddle;
        this.dispersionSum += el.dispersion;
        this.deviationSum += el.deviation;
      }
    );
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.fetchData();
  }

}
