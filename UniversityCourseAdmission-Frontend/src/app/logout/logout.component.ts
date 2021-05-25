import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {


  ngOnInit() {
  }

  // Success message of backend response is stored here after loggout
  successMessage:any;

  // error message of backend response is stored here if loggout failed
  errorMessage:any;

  constructor(authService:AuthenticationService) {
    if(authService.isLoggedIn()){
      authService.logout().subscribe(
        (result) => {
          localStorage.clear();
          this.successMessage="You have successfully logged out !";
        },
        (error) => {
          this.errorMessage= error.error;
        }
      );
    }
    else {
      this.errorMessage="You have not logged in! Please Log in to continue..";
    }  
   }

}
