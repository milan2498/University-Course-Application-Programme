import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionCommiteeMemberListComponent } from './admission-commitee-member-list.component';

describe('AdmissionCommiteeMemberListComponent', () => {
  let component: AdmissionCommiteeMemberListComponent;
  let fixture: ComponentFixture<AdmissionCommiteeMemberListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmissionCommiteeMemberListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmissionCommiteeMemberListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
