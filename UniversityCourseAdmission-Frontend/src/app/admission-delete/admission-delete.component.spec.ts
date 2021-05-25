import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionDeleteComponent } from './admission-delete.component';

describe('AdmissionDeleteComponent', () => {
  let component: AdmissionDeleteComponent;
  let fixture: ComponentFixture<AdmissionDeleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmissionDeleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmissionDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
