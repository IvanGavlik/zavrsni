import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {EventListComponent} from './components/event/event-list/event-list.component';
import {TeamComponent} from './components/team/team/team.component';
import {PoissonComponent} from './components/poisson/poisson/poisson.component';
import {SimulationComponent} from './components/simulation/simulation.component';
import {AssetComponent} from "./components/asset/asset.component";


const routes: Routes = [
  { path: '', redirectTo: 'poisson', pathMatch: 'full' },
  { path: 'events', component: EventListComponent },
  { path: 'team', component: TeamComponent },
  { path: 'poisson', component: PoissonComponent },
  { path: 'simulation', component: SimulationComponent },
  { path: 'assets', component: AssetComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
