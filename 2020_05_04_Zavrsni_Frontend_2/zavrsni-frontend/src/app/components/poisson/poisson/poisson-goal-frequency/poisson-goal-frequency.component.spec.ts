import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoissonGoalFrequencyComponent } from './poisson-goal-frequency.component';

describe('PoissonGoalFrequencyComponent', () => {
  let component: PoissonGoalFrequencyComponent;
  let fixture: ComponentFixture<PoissonGoalFrequencyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoissonGoalFrequencyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoissonGoalFrequencyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
