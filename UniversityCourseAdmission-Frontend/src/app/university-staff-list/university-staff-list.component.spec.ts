import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityStaffListComponent } from './university-staff-list.component';

describe('UniversityStaffListComponent', () => {
  let component: UniversityStaffListComponent;
  let fixture: ComponentFixture<UniversityStaffListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UniversityStaffListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversityStaffListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
