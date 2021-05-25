import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admission } from 'src/model/admission';
import { AdmissionCommiteeMember } from 'src/model/admissionCommiteeMember';
import { AdmissionCommiteeMemberServerService } from '../server-service/admission-commitee-member-server.service';

@Component({
  selector: 'app-admission-commitee-member-update',
  templateUrl: './admission-commitee-member-update.component.html',
  styleUrls: ['./admission-commitee-member-update.component.css']
})
export class AdmissionCommiteeMemberUpdateComponent implements OnInit {

  admissionCommiteeMember: AdmissionCommiteeMember = null;
  admissionDetails:Admission=null;
  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  constructor(private service: AdmissionCommiteeMemberServerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.route.paramMap.subscribe(
      (params) => {
        let acmid: number = +params.get('admissionCommiteeMemberId');

        this.service.getAdmissionCommiteeMember(acmid).subscribe(
          (data) => {
            this.admissionCommiteeMember = data
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  updated() {
    this.service.updateAdmissionCommiteeMember(this.admissionCommiteeMember).subscribe(
      (message) => {
        this.successMessage = message
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


  goBack() {
    this.router.navigate(["admission-commitee-member-list"]);
  }

}
