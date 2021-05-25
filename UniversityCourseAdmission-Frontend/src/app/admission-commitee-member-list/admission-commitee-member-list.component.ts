import { Component, OnInit } from '@angular/core';
import { AdmissionCommiteeMember } from 'src/model/admissionCommiteeMember';
import { AdmissionCommiteeMemberServerService } from '../server-service/admission-commitee-member-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-admission-commitee-member-list',
  templateUrl: './admission-commitee-member-list.component.html',
  styleUrls: ['./admission-commitee-member-list.component.css']
})
export class AdmissionCommiteeMemberListComponent implements OnInit {

  role: string = null;
  roleMessage: string = null;

  constructor(private service: AdmissionCommiteeMemberServerService, private loginService:AuthenticationService) { }

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()) {
      this.role = localStorage.getItem('role');
      if(this.role != 'COMMITEE' && this.role != 'ADMIN') {
        this.roleMessage = ' Access Denied for  '+this.role;
      }
    }
    this.loadData();
  }

  header: string = "List of Admission Commitee Members";

  admissionCommiteeMembers: AdmissionCommiteeMember[];
  message: string = null;
  errorMessage: string = null;


  delete(admissionCommiteeMemberId: number): void {
    this.service.deleteAdmissionCommiteeMember(admissionCommiteeMemberId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
    );

  }

  loadData(): void {

    this.service.getAdmissionComiteeMembers().subscribe(
      (data) => {
        this.admissionCommiteeMembers = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.details;
        //console.log(failResponse)
      }
    )
  }

  admissionCommiteeMemberName: any;
  Search() {
    if (this.admissionCommiteeMemberName == "") {
      this.ngOnInit();
    }
    else {
      this.admissionCommiteeMembers = this.admissionCommiteeMembers.filter(res => {
        return res.admissionCommiteeMemberName.toLocaleLowerCase().match(this.admissionCommiteeMemberName.toLocaleLowerCase());
      });
    }
  }

  key: string = 'id';
  reverse: boolean = false;
  sort(key) {
    this.key = key;
    this.reverse = !this.reverse;
  }

  setMyStyle() {
    let styles = {
      'background': 'linear-gradient( #dbe6f6, #c5796d)',
      'background-repeat': 'no-repeat'
    };
    return styles;
  }


}
