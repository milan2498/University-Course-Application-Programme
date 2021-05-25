import { Component, OnInit } from '@angular/core';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';
import { reduce } from 'rxjs/operators';
import { UniversityStaffs } from 'src/model/universityStaffs';
import { UniversityStaffServerService } from '../server-service/university-staff-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-university-staff-list',
  templateUrl: './university-staff-list.component.html',
  styleUrls: ['./university-staff-list.component.css']
})
export class UniversityStaffListComponent implements OnInit {
  constructor(private service: UniversityStaffServerService, private loginService:AuthenticationService) { }

  isLoggedin: boolean = false;
  role: string = null;
  roleMessage: string = null;

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()) {
      this.isLoggedin = true;
      this.role = localStorage.getItem('role');
      if(this.role != 'STAFF' && this.role != 'ADMIN' && this.role != 'COMMITEE') {
        this.roleMessage = ' Access Denied for  '+this.role;
      }
    }
    this.loadData();
  }

  header: string = "List of University Staff Members";

  staffs: UniversityStaffs[];
  message: string = null;
  errorMessage: string = null;


  roles: any[] = [
    { name: 'username' },
    { name: 'role' }
  ];

  delete(staffId: number): void {
    this.service.deleteStaff(staffId).subscribe(
      (response) => {
        this.message = response;
        this.loadData();
      },
      (error) => console.log(error)
      
    );

  }

  loadData(): void {

    this.service.getStaffs().subscribe(
      (data) => {
        this.staffs = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.details;
      }
    )
  }
  username:any;
  
  Search(){
    if (this.username==""){
      this.ngOnInit();
    }
    else{
      this.staffs=this.staffs.filter(res=>{
        return res.username.toLocaleLowerCase().match(this.username.toLocaleLowerCase());
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
     
      'background': 'linear-gradient( #dbe6f6, #c5796d)',
      'background-repeat': 'no-repeat'
     
    };
    return styles;
}
}
