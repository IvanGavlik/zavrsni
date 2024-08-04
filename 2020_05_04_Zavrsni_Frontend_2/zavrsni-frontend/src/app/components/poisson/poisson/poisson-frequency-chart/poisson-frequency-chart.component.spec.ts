import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoissonFrequencyChartComponent } from './poisson-frequency-chart.component';

describe('PoissonFrequencyChartComponent', () => {
  let component: PoissonFrequencyChartComponent;
  let fixture: ComponentFixture<PoissonFrequencyChartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoissonFrequencyChartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoissonFrequencyChartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
