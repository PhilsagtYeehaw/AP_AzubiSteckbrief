import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AzubiDetailComponent } from './azubi-detail.component';

describe('AzubiDetailComponent', () => {
  let component: AzubiDetailComponent;
  let fixture: ComponentFixture<AzubiDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AzubiDetailComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AzubiDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
