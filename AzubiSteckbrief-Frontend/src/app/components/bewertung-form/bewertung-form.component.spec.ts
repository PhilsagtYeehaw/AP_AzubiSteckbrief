import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BewertungFormComponent } from './bewertung-form.component';

describe('BewertungFormComponent', () => {
  let component: BewertungFormComponent;
  let fixture: ComponentFixture<BewertungFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BewertungFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BewertungFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
