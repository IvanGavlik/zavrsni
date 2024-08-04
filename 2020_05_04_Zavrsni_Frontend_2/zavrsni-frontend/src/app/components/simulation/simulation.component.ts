import { Component, OnInit } from '@angular/core';
import {SimulationService} from "./simulation.service";
import {PoissonCard} from "../poisson-card/poisson-card";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {MatchDialogComponent} from "../poisson-card/match-dialog/match-dialog.component";
import {SimulationDialogComponent} from "./simulation-dialog/simulation-dialog.component";

@Component({
  selector: 'app-simulation',
  templateUrl: './simulation.component.html',
  styleUrls: ['./simulation.component.css']
})
export class SimulationComponent implements OnInit {

  poissonCards: PoissonCard[] = [];

  constructor(private service: SimulationService,  private dialog: MatDialog) { }

  ngOnInit(): void {
    this.createMatch();
  }

  createMatch() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(SimulationDialogComponent, dialogConfig);

    dialogRef.afterOpened().subscribe(data => {
      this.poissonCards = [];
    });

    dialogRef.afterClosed().subscribe(data => {
      this.service.getSimulation().subscribe(el => {
        this.poissonCards = el;
      });
    });
  }


}
