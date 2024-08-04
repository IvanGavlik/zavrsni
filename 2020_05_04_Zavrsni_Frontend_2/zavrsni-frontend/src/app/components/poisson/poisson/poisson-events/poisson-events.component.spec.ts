import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoissonEventsComponent } from './poisson-events.component';

describe('PoissonEventsComponent', () => {
  let component: PoissonEventsComponent;
  let fixture: ComponentFixture<PoissonEventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoissonEventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoissonEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
