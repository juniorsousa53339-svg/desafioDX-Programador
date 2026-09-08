import { TestBed } from '@angular/core/testing';

import { Integrante } from './integrante';

describe('Integrante', () => {
  let service: Integrante;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Integrante);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
