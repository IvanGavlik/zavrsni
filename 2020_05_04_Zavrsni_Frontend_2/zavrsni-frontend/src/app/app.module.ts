import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { NavigationComponent } from './components/navigation/navigation.component';
import { DataTablesModule } from 'angular-datatables';
import {ChartsModule } from 'ng2-charts';
import { EventListComponent } from './components/event/event-list/event-list.component';
import { TeamComponent } from './components/team/team/team.component';
import { PoissonComponent } from './components/poisson/poisson/poisson.component';
import { TeamGoalsComponent } from './components/team/team-goals/team-goals.component';
import { TeamEventsComponent } from './components/team/team-events/team-events.component';
import { TeamSummaryComponent } from './components/team/team-summary/team-summary.component';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule } from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {MatStepperModule} from '@angular/material/stepper';
import {ReactiveFormsModule} from '@angular/forms';
import {MatButtonModule} from '@angular/material/button';
import { PoissonEventsComponent } from './components/poisson/poisson/poisson-events/poisson-events.component';
import { PoissonFrequencyChartComponent } from './components/poisson/poisson/poisson-frequency-chart/poisson-frequency-chart.component';
import { PoissonCombinationMatrixComponent } from './components/poisson/poisson/poisson-combination-matrix/poisson-combination-matrix.component';
import { MatPaginatorModule} from '@angular/material/paginator';
import { PoissonFrequencyChartTableComponent } from './components/poisson/poisson/poisson-frequency-chart/poisson-frequency-chart-table/poisson-frequency-chart-table.component';
import { PoissonGuessComponent } from './components/poisson/poisson/poisson-guess/poisson-guess.component';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { PoissonGoalFrequencyComponent } from './components/poisson/poisson/poisson-goal-frequency/poisson-goal-frequency.component';
import { LigaReviewGoalsComponent } from './components/liga-review-goals/liga-review-goals.component';
import {MatExpansionModule} from '@angular/material/expansion';
import { LigaReviewBestAttackComponent } from './components/liga-review-best-attack/liga-review-best-attack.component';
import { LigaReviewBestDefenseComponent } from './components/liga-review-best-defense/liga-review-best-defense.component';
import { LeagueStatisticsGoalsComponent } from './components/league-statistics-goals/league-statistics-goals.component';
import {MatTabsModule} from '@angular/material/tabs';
import { LeagueFrequencyChartComponent } from './components/league-frequency-chart/league-frequency-chart.component';
import { LeagueRelativeFrequencyChartComponent } from './components/league-relative-frequency-chart/league-relative-frequency-chart.component';
import { LeagueFrequencyPolygonComponent } from './components/league-frequency-polygon/league-frequency-polygon.component';
import { LeagueRelativeFrequencyPolygonComponent } from './components/league-relative-frequency-polygon/league-relative-frequency-polygon.component';
import { PoissonCardComponent } from './components/poisson-card/poisson-card.component';
import { PoissonCardDetailsComponent } from './components/poisson-card/poisson-card-details/poisson-card-details.component';
import { SimulationComponent } from './components/simulation/simulation.component';
import { MatchDialogComponent } from './components/poisson-card/match-dialog/match-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import { AssetComponent } from './components/asset/asset.component';
import { SimulationDialogComponent } from './components/simulation/simulation-dialog/simulation-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    EventListComponent,
    TeamComponent,
    PoissonComponent,
    TeamGoalsComponent,
    TeamEventsComponent,
    TeamSummaryComponent,
    PoissonEventsComponent,
    PoissonFrequencyChartComponent,
    PoissonCombinationMatrixComponent,
    PoissonFrequencyChartTableComponent,
    PoissonGuessComponent,
    SpinnerComponent,
    PoissonGoalFrequencyComponent,
    LigaReviewGoalsComponent,
    LigaReviewBestAttackComponent,
    LigaReviewBestDefenseComponent,
    LeagueStatisticsGoalsComponent,
    LeagueFrequencyChartComponent,
    LeagueRelativeFrequencyChartComponent,
    LeagueFrequencyPolygonComponent,
    LeagueRelativeFrequencyPolygonComponent,
    PoissonCardComponent,
    PoissonCardDetailsComponent,
    SimulationComponent,
    MatchDialogComponent,
    AssetComponent,
    SimulationDialogComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatSliderModule,
        HttpClientModule,
        DataTablesModule,
        ChartsModule,
        MatCardModule,
        MatFormFieldModule,
        MatSelectModule,
        MatTableModule,
        MatStepperModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatPaginatorModule,
        MatProgressSpinnerModule,
        MatExpansionModule,
        MatTabsModule,
      MatDialogModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
