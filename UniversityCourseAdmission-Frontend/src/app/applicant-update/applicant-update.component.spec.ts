import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicantUpdateComponent } from './applicant-update.component';

describe('ApplicantUpdateComponent', () => {
  let component: ApplicantUpdateComponent;
  let fixture: ComponentFixture<ApplicantUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicantUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicantUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
