import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoissonGuessComponent } from './poisson-guess.component';

describe('PoissonGuessComponent', () => {
  let component: PoissonGuessComponent;
  let fixture: ComponentFixture<PoissonGuessComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoissonGuessComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoissonGuessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
