import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/model/course';
import { CourseServerService } from '../server-service/course-server.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-course-create',
  templateUrl: './course-create.component.html',
  styleUrls: ['./course-create.component.css']
})
export class CourseCreateComponent implements OnInit {
  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;

  role: string = null;
  roleMessage: string = null;

  constructor(private service:CourseServerService,private loginService:AuthenticationService,
    private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    if(this.loginService.isLoggedIn()) {
      this.role = localStorage.getItem('role');
      if(this.role != 'STAFF' && this.role != 'ADMIN') {
        this.roleMessage = ' Access Denied for  '+this.role;
      }
    }
  } createNew(data: Course) {
    this.service.addCourses(data).subscribe(
      (message) => {
        this.successMessage = message;
        this.validationMessages = null;
        this.errorMessage = null;
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).details;
       /* if(failure.status==400){
          this.errorMessage="Same ID already exists.Please create with new ID."
        }*/
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
  
  goBack(){
    this.router.navigate(["course-create"]);
  }
}
