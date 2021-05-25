import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admission } from 'src/model/admission';
import { AdmissionServerService } from '../server-service/admission-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-admission-create',
  templateUrl: './admission-create.component.html',
  styleUrls: ['./admission-create.component.css']
})
export class AdmissionCreateComponent implements OnInit {
  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  role: string = null;
  roleMessage: string = null;

  constructor(private service:AdmissionServerService, private loginService:AuthenticationService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() : void {
    if(this.loginService.isLoggedIn()) {
      this.role = localStorage.getItem('role');
      if( this.role != 'COMMITEE' && this.role != 'ADMIN') {
        this.roleMessage = ' Access Denied for  '+this.role;
      }

    }
  }

  createNew(data: Admission) {
    this.service.addAdmission(data).subscribe(
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
   // console.log(data);
  }
  
  setMyStyle() {
    let styles = { 
      'background':'linear-gradient(  #6dd5fa, #ffffff,#2980b9)',
      'background-repeat':'no-repeat'
    };
    return styles;
}
}
