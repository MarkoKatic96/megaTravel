import { TestBed } from '@angular/core/testing';

import { IzlistajSertifikateService } from './izlistaj-sertifikate.service';

describe('IzlistajSertifikateService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IzlistajSertifikateService = TestBed.get(IzlistajSertifikateService);
    expect(service).toBeTruthy();
  });
});
