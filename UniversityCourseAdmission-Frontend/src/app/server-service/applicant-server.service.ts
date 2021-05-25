import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Applicant } from 'src/model/applicant';

@Injectable({
  providedIn: 'root'
})
export class ApplicantServerService {

  baseUrl: string = "http://localhost:8082/applicants"

  constructor(private http: HttpClient) { }
  getApplicants(): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(this.baseUrl);
  }
  getApplicant(applicantId: number):Observable<any> {
    //const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.get(`${this.baseUrl}/${applicantId}`);
  }
  updateApplicant(applicant: Applicant):Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.put(this.baseUrl, applicant, { responseType: 'text' });
  }
  deleteApplicant(appid: number): Observable<any> {
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.delete(`${this.baseUrl}/${appid}`, { responseType: 'text' });  // this.baseUrl+"/"+applicantId
  }
  addApplicant(newApp: Applicant) :Observable<any>{
    const headers = new HttpHeaders({ Authorization: localStorage.getItem('token') });
    return this.http.post(this.baseUrl, newApp, { responseType: 'text' });
  }
}
