import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Applicant } from 'src/model/applicant';
import { ApplicantServerService } from '../server-service/applicant-server.service';

@Component({
  selector: 'app-applicant-update',
  templateUrl: './applicant-update.component.html',
  styleUrls: ['./applicant-update.component.css']
})
export class ApplicantUpdateComponent implements OnInit {

  applicant:Applicant=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  genders: any[] = [
    { name: 'MALE' },
    { name: 'FEMALE' }
  ];
  constructor(private service:ApplicantServerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
        let appid: number = +params.get('applicantId');

        this.service.getApplicant(appid).subscribe(
          (data) => {
            this.applicant = data
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  updated() {
    this.service.updateApplicant(this.applicant).subscribe(
      (message) => {
        this.successMessage=message
        this.validationMessages = null
        this.errorMessage = null
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).errorMessage;
      }

    )

  }

  goBack(){
    this.router.navigate(["applicant-list"]);
  }

}
