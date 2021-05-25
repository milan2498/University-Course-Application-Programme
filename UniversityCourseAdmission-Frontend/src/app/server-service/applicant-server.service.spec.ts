import { TestBed } from '@angular/core/testing';

import { ApplicantServerService } from './applicant-server.service';

describe('ApplicantServerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ApplicantServerService = TestBed.get(ApplicantServerService);
    expect(service).toBeTruthy();
  });
});
