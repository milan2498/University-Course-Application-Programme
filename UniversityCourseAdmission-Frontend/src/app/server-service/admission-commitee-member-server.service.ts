import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AdmissionCommiteeMember } from 'src/model/admissionCommiteeMember';
import { Status } from 'src/model/status';

@Injectable({
  providedIn: 'root'
})
export class AdmissionCommiteeMemberServerService {

  baseUrl: string = "http://localhost:8082/AdmissionCommiteeMember"

  constructor(private http: HttpClient) { }
  getAdmissionComiteeMembers(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
  getAdmissionCommiteeMember(admissionCommiteeMemberId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/getUserByAdmissionCommiteeMemberId/${admissionCommiteeMemberId}`)
  }
  updateAdmissionCommiteeMember(admissionCommiteeMember: AdmissionCommiteeMember): Observable<any> {
    return this.http.put(this.baseUrl, admissionCommiteeMember, { responseType: 'text' });
  }

  updateAdmissionStatus(admissionId: number, status: string): Observable<any> {
    return this.http.patch(`${this.baseUrl}/updateAdmissionStatus/${admissionId}/${status}`, { responseType: 'text' })
  }
  deleteAdmissionCommiteeMember(admissionCommiteeMemberId: number): Observable<any> {
    //include responseType in options because response by default is JSON
    return this.http.delete(`${this.baseUrl}/deleteUserByAdmissionCommiteeMemberId/${admissionCommiteeMemberId}`, { responseType: 'text' })  // this.baseUrl+"/"+admissionCommiteeMemberId
  }
  addAdmissionCommiteeMember(newAdmissionCommiteeMember: AdmissionCommiteeMember): Observable<any> {
    return this.http.post(this.baseUrl, newAdmissionCommiteeMember, { responseType: 'text' });

  }
}
