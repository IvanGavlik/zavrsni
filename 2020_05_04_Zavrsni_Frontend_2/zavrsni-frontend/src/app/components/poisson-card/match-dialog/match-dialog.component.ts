import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-match-dialog',
  templateUrl: './match-dialog.component.html',
  styleUrls: ['./match-dialog.component.css']
})
export class MatchDialogComponent implements OnInit {
  teams: string[] = [ 'Rangers FC', 'Celtic FC', 'Aberdeen FC',
    'Hibernian FC', 'Kilmarnock FC', 'Motherwell FC', 'Heart of Midlothian FC',
    'Livingston FC', 'St Johnstone FC', 'Saint Mirren FC', 'Dundee FC',
    'Hamilton Academical FC'
  ];

  selectedHomeTeam: string;
  selectedGuessTeam: string;


  constructor(private dialogRef: MatDialogRef<MatchDialogComponent>, private route: Router) { }

  ngOnInit(): void {}

  save() {
    if (!this.selectedHomeTeam) {
      alert('Izaberi domaina');
      return;
    }
    if (!this.selectedGuessTeam) {
      alert('Izaberi gost');
      return;;
    }

    if (this.selectedHomeTeam === this.selectedGuessTeam) {
      alert('Domaćin i gost ne mogu biti isti');
      return;
    }

    this.dialogRef.close({home: this.selectedHomeTeam, guess: this.selectedGuessTeam});
  }

  cancel() {
    this.dialogRef.close();
    this.route.navigateByUrl('events');
  }
}
