import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { tokenNotExpired } from 'angular2-jwt';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  baseUrl: String = 'http://localhost:8082/';
  Role: string;

  constructor(private http: HttpClient) {
  }

  // This method request to the back end using given url and get JWT token and 
  // set user as LoggedIn into the database and
  // store that in localStorage of session
  login(credentials) {
    let url = this.baseUrl + 'login';
    return this.http.post<any>(url, credentials).pipe(
      map(
        response => {
          if (response && response.token) {
            let tokenStr = 'Bearer ' + response.token;
            localStorage.setItem('token', tokenStr);
            return response;
          }
        },
        error => error
      )
    );
  }

  // This method request to the back end using given url and set token in header's Authorization and
  // set user as Loggedout into the database and
  // clear the localStoreage of session
  logout() {
    let token = localStorage.getItem('token');
    let url = this.baseUrl + 'remove';
    let headers = new HttpHeaders({'Authorization' : token});
    return this.http.get(url, {headers, responseType:'text'});
  }

  // This method checks that if a user is loggedin currentaly or not 
  // as well as check token is expired or not
  isLoggedIn() {
    let token = localStorage.getItem('token');

    if (!token)
      return false;

    return tokenNotExpired(null,token);
  }

  // This method give the loggedin user details using the token
  getUserDetails(): Observable<any> {
    let token = localStorage.getItem('token');
    let url = this.baseUrl + 'getUserDetails';
    let headers = new HttpHeaders({ 'Authorization' : token });
    return this.http.get(url, {headers});
  }

  // This method change password for the current loggedin user using token and
  // give a text response message
  changePassword(credentials): Observable<any> {
    let token = localStorage.getItem('token');
    let url = this.baseUrl + 'changePassword';
    let headers = new HttpHeaders({ 'Authorization' : token});
    return this.http.put(url, credentials, {headers, responseType:'text'});
  }

  // This method give the role of current loggedin user
  getRole(): Observable<any> {
    let token = localStorage.getItem('token');
    let url = this.baseUrl + 'getRole';
    let headers = new HttpHeaders({ 'Authorization' : token});
    return this.http.get(url, {headers, responseType: 'text'});
  }
}
