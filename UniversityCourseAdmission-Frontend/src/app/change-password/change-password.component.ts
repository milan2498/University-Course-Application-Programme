import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  // Response message of backend server is stored here after successfully changed password
  successMessage: string = null;

  // Response message of backend server is stored here after changed password failed
  errorMessage: string = null;

  // If a user is loggedin or not checking
  invalidLogin: boolean = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService) { }

  ngOnInit() {
    if(!this.authenticationService.isLoggedIn()) {
      this.errorMessage = "You have not logged in yet!!";
    }
  }

  // This method is called on the submit of the Submit button of change password form in html template
  // and this method calls change password of Service
  changePassword(credentials) {
    this.authenticationService.changePassword(credentials).subscribe(
        (result) => {
        this.successMessage = result;      
      },
         (fail) => {
          this.invalidLogin = true;
          this.errorMessage = fail.error.details;
        }
      );
  }

  setMyStyle() {
    let styles = { 
      'background':'linear-gradient(  #6dd5fa, #ffffff,#2980b9)',
      'background-repeat':'no-repeat'
    };
    return styles;
  }
}
