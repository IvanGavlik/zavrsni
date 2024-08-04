import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {Asset} from "./asset";
import {AssetService} from "./asset.service";

@Component({
  selector: 'app-asset',
  templateUrl: './asset.component.html',
  styleUrls: ['./asset.component.css']
})
export class AssetComponent implements OnInit, OnDestroy {

  // 45-20-45-10
  displayedColumns = [ 'eventDay', 'homeTeam', 'score', 'guestTeam'];
  page: Asset[] = [];
  all: Subscription;

  constructor(private service: AssetService) { }

  ngOnInit(): void {
    this.all = this.service.getAssetAll().subscribe(data => {
      this.page = data;
    })
  }

  ngOnDestroy(): void {
    if (this.all) {
      this.all.unsubscribe()
    }
  }


}
