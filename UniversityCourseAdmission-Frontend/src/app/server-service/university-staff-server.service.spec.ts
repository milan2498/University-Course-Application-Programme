import { TestBed } from '@angular/core/testing';

import { UniversityStaffServerService } from './university-staff-server.service';

describe('UniversityStaffServerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UniversityStaffServerService = TestBed.get(UniversityStaffServerService);
    expect(service).toBeTruthy();
  });
});
