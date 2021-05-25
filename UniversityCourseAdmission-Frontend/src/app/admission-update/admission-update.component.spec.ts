import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmissionUpdateComponent } from './admission-update.component';

describe('AdmissionUpdateComponent', () => {
  let component: AdmissionUpdateComponent;
  let fixture: ComponentFixture<AdmissionUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdmissionUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmissionUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
