import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { Router } from "@angular/router";
import { AppComponent } from '../app.component';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // This variable is used to hide/show the user login form according to user loggedin status
  invalidLogin: boolean;

  // error message of backend response is stored here
  errorMessage: string;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    ) { }

    ngOnInit() {
      if(this.authenticationService.isLoggedIn()) {
        this.router.navigate(['/profile']);
      }
    }

  signIn(credentials) {
    this.authenticationService.login(credentials)
      .subscribe(result => {
        this.router.navigate(['/profile']);
      },
         fail => {
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
