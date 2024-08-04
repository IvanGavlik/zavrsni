import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Asset} from "./asset";

@Injectable({
  providedIn: 'root'
})
export class AssetService {

  constructor(private httpClient: HttpClient) { }

  getAssetAll(): Observable<Asset[]> {
    const url = 'https://nameless-river-96396.herokuapp.com/v3/asset-all';
 //   const url = 'http://localhost:8080/v3/asset-all';
    return this.httpClient.get<Asset[]>(url);
  }
}
