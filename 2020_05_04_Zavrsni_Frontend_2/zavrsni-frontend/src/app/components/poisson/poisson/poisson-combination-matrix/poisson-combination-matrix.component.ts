import {Component, Input, OnInit} from '@angular/core';
import {PoissonMatrix} from "../../../../model/PoissonStatistics";

@Component({
  selector: 'app-poisson-combination-matrix',
  templateUrl: './poisson-combination-matrix.component.html',
  styleUrls: ['./poisson-combination-matrix.component.css']
})
export class PoissonCombinationMatrixComponent implements OnInit {

  @Input() poissonMatrix: PoissonMatrix;
  @Input() homeTeamId: string;
  @Input() guestTeamId: string;

  constructor() { }

  ngOnInit(): void {

  }

}
