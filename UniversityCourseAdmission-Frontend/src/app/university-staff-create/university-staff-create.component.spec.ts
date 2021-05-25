import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversityStaffCreateComponent } from './university-staff-create.component';

describe('UniversityStaffCreateComponent', () => {
  let component: UniversityStaffCreateComponent;
  let fixture: ComponentFixture<UniversityStaffCreateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UniversityStaffCreateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UniversityStaffCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
