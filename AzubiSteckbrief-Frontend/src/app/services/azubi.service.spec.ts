import { TestBed } from '@angular/core/testing';

import { AzubiService } from './azubi.service';

describe('AzubiService', () => {
  let service: AzubiService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AzubiService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
