import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NotenTabelleComponent } from './noten-tabelle.component';

describe('NotenTabelleComponent', () => {
  let component: NotenTabelleComponent;
  let fixture: ComponentFixture<NotenTabelleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NotenTabelleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NotenTabelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
