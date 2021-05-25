import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UniversityStaffs } from 'src/model/universityStaffs';

@Injectable({
  providedIn: 'root'
})
export class UniversityStaffServerService {

  constructor(private http:HttpClient) { }
  baseUrl:string='http://localhost:8082/UniversityStaffs'
  getStaffs(): Observable<any> {
    const headers=new HttpHeaders({Authorization:localStorage.getItem('token')});
    return this.http.get(this.baseUrl,{headers});
  }

  getStaff(staffId: number) : Observable<any>{
    const headers=new HttpHeaders({Authorization:localStorage.getItem('token')});
    return this.http.get(`${this.baseUrl}/${staffId}`,{headers});
  }

  updateStaff(staff: UniversityStaffs): Observable<any> {
    const headers=new HttpHeaders({Authorization:localStorage.getItem('token')});
    return this.http.put(this.baseUrl,staff,{ headers , responseType:'text'});
  }

  deleteStaff(staffId: number): Observable<any> {
    const headers=new HttpHeaders({Authorization:localStorage.getItem('token')});
    return this.http.delete(`${this.baseUrl}/${staffId}`,{ headers , responseType:'text'});
  }

  addStaff(newStaff: UniversityStaffs): Observable<any> {
    const headers=new HttpHeaders({Authorization:localStorage.getItem('token')});
    return this.http.post(this.baseUrl,newStaff,{ headers , responseType:'text'});
  }
}
