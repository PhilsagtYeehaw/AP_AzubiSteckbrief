import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SozialverhaltenTabelleComponent } from './sozialverhalten-tabelle.component';

describe('SozialverhaltenTabelleComponent', () => {
  let component: SozialverhaltenTabelleComponent;
  let fixture: ComponentFixture<SozialverhaltenTabelleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SozialverhaltenTabelleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SozialverhaltenTabelleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
