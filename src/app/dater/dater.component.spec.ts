import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DaterComponent } from './dater.component';

describe('DaterComponent', () => {
  let component: DaterComponent;
  let fixture: ComponentFixture<DaterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DaterComponent]
    });
    fixture = TestBed.createComponent(DaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
