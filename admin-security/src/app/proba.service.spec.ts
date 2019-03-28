import { TestBed } from '@angular/core/testing';

import { ProbaService } from './proba.service';

describe('ProbaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProbaService = TestBed.get(ProbaService);
    expect(service).toBeTruthy();
  });
});
