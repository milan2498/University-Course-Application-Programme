import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Applicant } from 'src/model/applicant';
import { ApplicantServerService } from '../server-service/applicant-server.service';

@Component({
  selector: 'app-applicant-create',
  templateUrl: './applicant-create.component.html',
  styleUrls: ['./applicant-create.component.css']
})
export class ApplicantCreateComponent implements OnInit {
  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  constructor(private service:ApplicantServerService) { }

  ngOnInit() {
  }
  roles: any[]=[
    {name:'APPLICANT'}
  ];
  genders: any[] = [
    { name: 'MALE' },
    { name: 'FEMALE' }
  ];
  courses:any[]=[
    {number:110},
    {number:120},
    {number:130},
    {number:140},
    {number:150},
    {number:200},
    {number:210},
    {number:220},
    {number:300},
    {number:310},
    {number:320},
    {number:330},
    {number:340},
  ];
  createNew(data: Applicant) {
    this.service.addApplicant(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).details;
      }
    )
  }
  setMyStyle() {
    let styles = { 
      'background':'linear-gradient(  #6dd5fa, #ffffff,#2980b9)',
      'background-repeat':'no-repeat'
    };
    return styles;
}

}
