import { TestBed } from '@angular/core/testing';

import { IzbrisiSertifikatService } from './izbrisi-sertifikat.service';

describe('IzbrisiSertifikatService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: IzbrisiSertifikatService = TestBed.get(IzbrisiSertifikatService);
    expect(service).toBeTruthy();
  });
});
