import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';


const baseUrl = 'http://localhost:9090/api/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _authSub$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  constructor(private http: HttpClient) { }

  public get isAuthenticated$(): Observable<boolean> {
    return this._authSub$.asObservable();
  }

  login(data:any): Observable<any> {
    return this.http.post(baseUrl+"/signin", data);
  }

  signup(data:any): Observable<any> {
    return this.http.post(baseUrl+"/signup", data);
  }

  logout(){

  }
  
}

