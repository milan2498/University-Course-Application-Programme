import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionCommiteeMemberStatusComponent } from './admission-commitee-member-status.component';

describe('AdmissionCommiteeMemberStatusComponent', () => {
  let component: AdmissionCommiteeMemberStatusComponent;
  let fixture: ComponentFixture<AdmissionCommiteeMemberStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmissionCommiteeMemberStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmissionCommiteeMemberStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
