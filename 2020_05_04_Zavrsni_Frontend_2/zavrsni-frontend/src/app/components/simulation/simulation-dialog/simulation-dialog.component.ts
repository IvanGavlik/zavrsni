import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {MatchDialogComponent} from "../../poisson-card/match-dialog/match-dialog.component";

@Component({
  selector: 'app-simulation-dialog',
  templateUrl: './simulation-dialog.component.html',
  styleUrls: ['./simulation-dialog.component.css']
})
export class SimulationDialogComponent  {

  constructor(private dialogRef: MatDialogRef<MatchDialogComponent>, private route: Router) { }

  save() {
    this.dialogRef.close();
  }

  cancel() {
    this.dialogRef.close();
    this.route.navigateByUrl('events');
  }

}
