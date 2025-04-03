import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VermittlungsstrukturComponent } from './vermittlungsstruktur.component';

describe('VermittlungsstrukturComponent', () => {
  let component: VermittlungsstrukturComponent;
  let fixture: ComponentFixture<VermittlungsstrukturComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VermittlungsstrukturComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VermittlungsstrukturComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
