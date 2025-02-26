import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AzubiListComponent } from './azubi-list.component';

describe('AzubiListComponent', () => {
  let component: AzubiListComponent;
  let fixture: ComponentFixture<AzubiListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AzubiListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AzubiListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
