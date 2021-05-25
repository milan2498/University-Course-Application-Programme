import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admission } from 'src/model/admission';
import { AdmissionServerService } from '../server-service/admission-server.service';

@Component({
  selector: 'app-admission-delete',
  templateUrl: './admission-delete.component.html',
  styleUrls: ['./admission-delete.component.css']
})
export class AdmissionDeleteComponent implements OnInit {
  admission: Admission=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  admissionId: number;
  constructor(private service: AdmissionServerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params) => {
        let aid: number = +params.get('admissionId');

        this.service.deleteAdmission(aid).subscribe(
          (data) => {
            this.admission = data
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  delete() {
    this.service.deleteAdmission(this.admissionId).subscribe(
      (message) => {
        this.successMessage=message
        this.validationMessages = null
        this.errorMessage = null
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).details;
      }
    )
  }

  goBack(){
    this.router.navigate(["admission-list"]);
  }

}
