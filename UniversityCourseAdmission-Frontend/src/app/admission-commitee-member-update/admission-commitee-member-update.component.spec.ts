import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionCommiteeMemberUpdateComponent } from './admission-commitee-member-update.component';

describe('AdmissionCommiteeMemberUpdateComponent', () => {
  let component: AdmissionCommiteeMemberUpdateComponent;
  let fixture: ComponentFixture<AdmissionCommiteeMemberUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmissionCommiteeMemberUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmissionCommiteeMemberUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
