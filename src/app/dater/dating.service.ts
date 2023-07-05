import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatingService implements HttpInterceptor{

  private baseUrl = 'http://localhost:9090/api/profile';
  private token:String=''
  constructor(private http: HttpClient,private cookieService:CookieService) { 
    this.token=this.cookieService.get('token');
  }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: 'Bearer '+this.token
      }
    });

    return next.handle(request);
  }

  upload(file: File): Observable<HttpEvent<any>> {
    console.log(file.name);
    
    const formData: FormData = new FormData();
    formData.append('files', file);
    formData.append('id',this.cookieService.get('id'));

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }

  createProfile(profileDto:any){
    return this.http.post(this.baseUrl+"/profile", profileDto);
  }

  deleteProfile(profileDto:any){
    return this.http.post(this.baseUrl+"/remove", profileDto);
  }

  
}
