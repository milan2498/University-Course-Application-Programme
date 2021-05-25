import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admission } from 'src/model/admission';

@Injectable({
  providedIn: 'root'
})
export class AdmissionServerService {

  baseUrl: string = "http://localhost:8082/Admission"

  constructor(private http: HttpClient) { }
  getAdmissions(): Observable<any> {
    return this.http.get(this.baseUrl);
  }
  getAdmission(admissionId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${admissionId}`)
  }
  updateAdmission(admission: Admission): Observable<any> {
    return this.http.put(this.baseUrl, admission, { responseType: 'text' });
  }
  deleteAdmission(admissionId: number): Observable<any> {
    //include responseType in options because response by default is JSON
    return this.http.delete(`${this.baseUrl}/${admissionId}`, { responseType: 'text' })  
    }
  addAdmission(newAdmission: Admission): Observable<any> {
    return this.http.post(this.baseUrl, newAdmission, { responseType: 'text' });
  }
}
