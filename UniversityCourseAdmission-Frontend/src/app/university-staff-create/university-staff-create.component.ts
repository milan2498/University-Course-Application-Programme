import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UniversityStaffs } from 'src/model/universityStaffs';
import { UniversityStaffServerService } from '../server-service/university-staff-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-university-staff-create',
  templateUrl: './university-staff-create.component.html',
  styleUrls: ['./university-staff-create.component.css']
})
export class UniversityStaffCreateComponent implements OnInit {
  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  p:number=1;

  role: string = null;
  roleMessage: string = null;
  
  constructor(private service: UniversityStaffServerService, private loginService:AuthenticationService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    if(this.loginService.isLoggedIn()) {
      this.role = localStorage.getItem('role');
      if(this.role != 'ADMIN') {
        this.roleMessage = ' Access Denied for  '+this.role;
      }
    }
  }
  roles: any[] = [
    { name: 'STAFF' }
    //{ name: 'COMMITEE' }
  ];

  createNew(data: UniversityStaffs) {
    this.service.addStaff(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        //this.errorMessage = JSON.parse(failure.error).errorMessage;
        /*if (failure.status == 400) {
          this.errorMessage = failure.error.details;
        }*/
        this.errorMessage = JSON.parse(failure.error).details;
      }

    )
    //console.log(data);
  
  }
  goBack(){
    this.router.navigate(["staff-list"]);
  }
  setMyStyle() {
    let styles = { 
      'background':'linear-gradient(  #6dd5fa, #ffffff,#2980b9)',
      'background-repeat':'no-repeat'
    };
    return styles;
}
}
