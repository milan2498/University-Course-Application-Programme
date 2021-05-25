import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Online University Management System';
  username: string = null;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    // Add active class to the current button (highlight it)
    let header = document.getElementById("myDIV");
    let btns = header.getElementsByClassName("btn");
    for (var i = 0; i < btns.length; i++) {
      btns[i].addEventListener("click", function () {
        var current = document.getElementsByClassName("active");
        if (current.length > 0) {
          current[0].className = current[0].className.replace(" active", "");
        }
        this.className += " active";
      });
    }
  }

  setWelcomeUser() {
    if (this.authenticationService.isLoggedIn()) {
      this.username = localStorage.getItem('username');
    }
  }

}
