import { TestBed } from '@angular/core/testing';

import { AdmissionServerService } from './admission-server.service';

describe('AdmissionServerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdmissionServerService = TestBed.get(AdmissionServerService);
    expect(service).toBeTruthy();
  });
});
