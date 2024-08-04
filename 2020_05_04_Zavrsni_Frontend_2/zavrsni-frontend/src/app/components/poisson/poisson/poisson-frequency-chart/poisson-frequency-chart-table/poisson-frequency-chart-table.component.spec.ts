import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoissonFrequencyChartTableComponent } from './poisson-frequency-chart-table.component';

describe('PoissonFrequencyChartTableComponent', () => {
  let component: PoissonFrequencyChartTableComponent;
  let fixture: ComponentFixture<PoissonFrequencyChartTableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoissonFrequencyChartTableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoissonFrequencyChartTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
