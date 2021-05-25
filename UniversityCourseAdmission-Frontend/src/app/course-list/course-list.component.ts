import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/model/course';
import { ApplicantServerService } from '../server-service/applicant-server.service';
import { CourseServerService } from '../server-service/course-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  constructor(private service:CourseServerService,service2:ApplicantServerService,private loginService:AuthenticationService,private route: ActivatedRoute, private router: Router) { }
  
  isLoggedin:boolean = false;
  role: string = null;

  ngOnInit(): void { 
    if(this.loginService.isLoggedIn()) {
      this.isLoggedin = true;
      this.role = localStorage.getItem('role');
    }
    this.loadData();
  }
  header: string = "List of Courses";

  courses: Course[];
  message: string = null;
  errorMessage: string = null;
  

  loadData(): void {

    this.service.getCourses().subscribe(
      (data) => {
        this.courses = data;
        this.errorMessage = null;
      },
      (failResponse) => {
        this.errorMessage = failResponse.error.details;
      }
    )
  }




goApply(){
  this.router.navigate(["applicant-create"]);
}
courseName:any;
  Search(){
    if (this.courseName==""){
      this.ngOnInit();
    }
    else{
      this.courses=this.courses.filter(res=>{
        return res.courseName.toLocaleLowerCase().match(this.courseName.toLocaleLowerCase());
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
      'background':'linear-gradient(to left, #dbe6f6, #c5796d)',
      'background-repeat':'no-repeat'
    };
    return styles;
}
}
