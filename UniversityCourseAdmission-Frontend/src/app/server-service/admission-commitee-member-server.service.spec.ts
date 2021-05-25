import { TestBed } from '@angular/core/testing';

import { AdmissionCommiteeMemberServerService } from './admission-commitee-member-server.service';

describe('AdmissionCommiteeMemberServerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdmissionCommiteeMemberServerService = TestBed.get(AdmissionCommiteeMemberServerService);
    expect(service).toBeTruthy();
  });
});
