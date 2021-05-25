import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionCommiteeMemberCreateComponent } from './admission-commitee-member-create.component';

describe('AdmissionCommiteeMemberCreateComponent', () => {
  let component: AdmissionCommiteeMemberCreateComponent;
  let fixture: ComponentFixture<AdmissionCommiteeMemberCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmissionCommiteeMemberCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmissionCommiteeMemberCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
