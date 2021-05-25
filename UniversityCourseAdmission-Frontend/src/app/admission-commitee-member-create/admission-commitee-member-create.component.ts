import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdmissionCommiteeMember } from 'src/model/admissionCommiteeMember';
import { UniversityStaffs } from 'src/model/universityStaffs';
import { AdmissionCommiteeMemberServerService } from '../server-service/admission-commitee-member-server.service';
import { UniversityStaffServerService } from '../server-service/university-staff-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-admission-commitee-member-create',
  templateUrl: './admission-commitee-member-create.component.html',
  styleUrls: ['./admission-commitee-member-create.component.css']
})
export class AdmissionCommiteeMemberCreateComponent implements OnInit {
  

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  flag: boolean;

  commiteeMember:AdmissionCommiteeMember=null;
  staffs:UniversityStaffs;
  role: string = null;
  roleMessage: string = null;


  constructor(private service: AdmissionCommiteeMemberServerService,private staffService: UniversityStaffServerService, private loginService:AuthenticationService,private route: ActivatedRoute, private router: Router) {
    

  }

  ngOnInit() {
    if (this.loginService.isLoggedIn()) {
      this.role = localStorage.getItem('role');
      if (this.role != 'COMMITEE' && this.role != 'ADMIN') {
        this.roleMessage = ' Access Denied for  ' + this.role;
      }
    }
   
    this.loadData();
  }
  loadData(): void {

    this.staffService.getStaffs().subscribe(
      (data) => {
        this.staffs = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.details;
      }
    )
  }
  // roles: any[] = [
  //   { name: 'STAFF' },
  //   { name: 'COMMITEE' }
  // ];

  // existsData(): void{
  //   if (!document.getElementById("acmid") && !document.getElementById("staffid")) {
  //     this.flag = true;
  //   } else {
  //     this.flag = false;
  //   }
  // }
  


  createNew(data: AdmissionCommiteeMember) {
   this.service.addAdmissionCommiteeMember(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
        console.log(data)
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        // this.errorMessage = JSON.parse(failure.error).message;
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
