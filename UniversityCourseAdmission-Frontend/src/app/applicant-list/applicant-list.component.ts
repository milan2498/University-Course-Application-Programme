import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Applicant } from 'src/model/applicant';
import { ApplicantServerService } from '../server-service/applicant-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-applicant-list',
  templateUrl: './applicant-list.component.html',
  styleUrls: ['./applicant-list.component.css']
})
export class ApplicantListComponent implements OnInit {

  constructor(private service: ApplicantServerService,private service2:AuthenticationService) { }
  isLoggedin:boolean;
  header: string = "List of Applicants";
  role: string = null;
  
  ngOnInit(): void {
    this.loadData();
    if(this.service2.isLoggedIn()) {
      this.isLoggedin = true;
      this.role = localStorage.getItem('role');
    }
  }
  applicants: Applicant[];
  message: string = null;
  errorMessage: string = null;

  delete(applicantId: number): void {
    this.service.deleteApplicant(applicantId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  loadData(): void {

    this.service.getApplicants().subscribe(
      (data) => {
        this.applicants = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.details;
      }
    )
  }
  applicantDegree:any;
  Search(){
    if (this.applicantDegree==""){
      this.ngOnInit();
    }
    else{
      this.applicants=this.applicants.filter(res=>{
        return res.applicantDegree.toLocaleLowerCase().match(this.applicantDegree.toLocaleLowerCase());
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
      'background':'linear-gradient(#dbe6f6, #c5796d)',
      'background-repeat':'no-repeat'
    };
    return styles;
}
}
