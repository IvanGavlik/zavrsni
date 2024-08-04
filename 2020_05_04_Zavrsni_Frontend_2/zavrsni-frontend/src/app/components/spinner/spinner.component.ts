import {Component, OnInit} from '@angular/core';
import {SpinnerService} from "../../service/spinner.service";
import {Spinner} from "../../model/spinner";

@Component({
  selector: 'app-spinner',
  templateUrl: './spinner.component.html',
  styleUrls: ['./spinner.component.css']
})
export class SpinnerComponent implements OnInit {

  spinner: Spinner;

  constructor(private spinnerService: SpinnerService) { }

  ngOnInit(): void {
    this.spinnerService.getSpinner().subscribe(
      res => {
        this.spinner = res;
    //    alert('get spinner::'+  res.display + " " +res.msg);
      }
    )
  }

}
