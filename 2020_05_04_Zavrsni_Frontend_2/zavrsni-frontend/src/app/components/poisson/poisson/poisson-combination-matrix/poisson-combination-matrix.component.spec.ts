import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PoissonCombinationMatrixComponent } from './poisson-combination-matrix.component';

describe('PoissonCombinationMatrixComponent', () => {
  let component: PoissonCombinationMatrixComponent;
  let fixture: ComponentFixture<PoissonCombinationMatrixComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PoissonCombinationMatrixComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PoissonCombinationMatrixComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
