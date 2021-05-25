import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Course } from 'src/model/course';
import { CourseServerService } from '../server-service/course-server.service';

@Component({
  selector: 'app-course-delete',
  templateUrl: './course-delete.component.html',
  styleUrls: ['./course-delete.component.css']
})
export class CourseDeleteComponent implements OnInit {
  course: Course=null;

  validationMessages: string[] = null;
  errorMessage: string = null;
  successMessage: string = null;
  courseId: number;
  constructor(private service: CourseServerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.route.paramMap.subscribe(
      (params) => {
        let cid: number = +params.get('courseId');

        this.service.deleteCourses(cid).subscribe(
          (data) => {
            this.course = data
          },
          (fail) => {
            this.errorMessage = fail.error.errorMessage;
          }
        )
      }
    )
  }

  delete() {
    this.service.deleteCourses(this.courseId).subscribe(
      (message) => {
        this.successMessage=message
        this.validationMessages = null
        this.errorMessage = null
      },
      (failure) => {
        this.successMessage = null;
        this.validationMessages = JSON.parse(failure.error).errors;
        this.errorMessage = JSON.parse(failure.error).errorMessage;
      }
  
    )

  }


  goBack(){
    this.router.navigate(["course-list"]);
  }
}