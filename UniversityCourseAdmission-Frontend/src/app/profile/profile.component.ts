import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDetails } from 'src/model/userDetails';
import { AppComponent } from '../app.component';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  // UserDetails is an interface in which current loggedin user details will be stored
  // that will come from the backend using getDetails() of AuthenticationService service
  userdetails: UserDetails = null;

  // error message of backend response is stored here
  errorMessage: String = null;

  // success message of backend response is stored here
  successMessage:string=null;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService,
    private root: AppComponent
  ) { }

  ngOnInit() {
    if(this.authenticationService.isLoggedIn()) {
      
      this.authenticationService.getUserDetails().subscribe(
        (result) => {
          this.userdetails = result;
          localStorage.setItem('username', this.userdetails.username);
          this.successMessage="You have successfully logged in.";
        },
        (fail) => {
          this.errorMessage = fail.error.errorMessage;
          
        }
      );

      this.authenticationService.getRole().subscribe(
        (result) => {
          localStorage.setItem('role', result);
        },
        (fail) => {
          console.log(fail);
        }
      )

      this.root.setWelcomeUser();
    }
    else {
      this.errorMessage = "You have not logged in! Please Log in.";
    }
  }
  setMyStyle() {
    let styles = {
      'background':'linear-gradient(#dbe6f6, #c5796d)',
      'background-repeat':'no-repeat'
    };
    return styles;
  }
}
