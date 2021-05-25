import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityStaffUpdateComponent } from './university-staff-update.component';

describe('UniversityStaffUpdateComponent', () => {
  let component: UniversityStaffUpdateComponent;
  let fixture: ComponentFixture<UniversityStaffUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UniversityStaffUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversityStaffUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
