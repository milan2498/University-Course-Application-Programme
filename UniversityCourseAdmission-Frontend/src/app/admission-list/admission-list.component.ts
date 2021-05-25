import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Admission } from 'src/model/admission';
import { AdmissionServerService } from '../server-service/admission-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-admission-list',
  templateUrl: './admission-list.component.html',
  styleUrls: ['./admission-list.component.css']
})
export class AdmissionListComponent implements OnInit {

  constructor(private service: AdmissionServerService, private service2: AuthenticationService, private route: ActivatedRoute, private router: Router) { }

  header: string = "List of Admissions";

  isLoggedin: boolean = false;
  role: string = null;
  roleMessage: string = null;

  ngOnInit(): void {
    if(this.service2.isLoggedIn()) {
      this.role = localStorage.getItem('role');
      if( this.role != 'ADMIN' && this.role != 'COMMITEE' && this.role != 'APPLICANT') {
        this.roleMessage = ' Access Denied for  '+this.role;
      }
    }
    this.loadData();
  }

  admissions: Admission[];
  message: string = null;
  errorMessage: string = null;

  delete(admisssionId: number): void {
    this.service.deleteAdmission(admisssionId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  loadData(): void {
    this.service.getAdmissions().subscribe(
      (data) => {
        this.admissions = data;
        this.errorMessage = null;
        //console.log(data);
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.details;
      }
    )
  }
  status:any;
  Search(){
    if (this.status==""){
      this.ngOnInit();
    }
    else{
      this.admissions=this.admissions.filter(res=>{
        return res.status.toLocaleLowerCase().match(this.status.toLocaleLowerCase());
      });
    }
  }
  key:string='id';
  reverse:boolean=false;
  sort(key){
    this.key=key;
    this.reverse=!this.reverse;
  }

  setMyStyle() {
    let styles = { 
      'background':'linear-gradient(to left,  #dbe6f6, #c5796d)',
      'background-repeat':'no-repeat'
    };
    return styles;
}
}