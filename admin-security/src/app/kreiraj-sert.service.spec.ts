import { TestBed } from '@angular/core/testing';

import { KreirajSertService } from './kreiraj-sert.service';

describe('KreirajSertService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: KreirajSertService = TestBed.get(KreirajSertService);
    expect(service).toBeTruthy();
  });
});
