import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardService {

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
) { }

// AuthGuard method to check valid user to access requested service
canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    
    if (this.authenticationService.isLoggedIn()) {
        // logged in so return true
        return true;
    }

    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login']);
    return false;
  }
}
