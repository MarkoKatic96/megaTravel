import { TestBed } from '@angular/core/testing';

import { PoveziSertifikateService } from './povezi-sertifikate.service';

describe('PoveziSertifikateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PoveziSertifikateService = TestBed.get(PoveziSertifikateService);
    expect(service).toBeTruthy();
  });
});
