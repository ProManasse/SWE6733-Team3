import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DatingService implements HttpInterceptor{

  private baseUrl = 'http://localhost:9090/api/profile';

  constructor(private http: HttpClient) { }
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        Authorization: `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtcm9nZXIiLCJpYXQiOjE2ODc5OTMxMzUsImV4cCI6MTY4ODA3OTUzNX0.QoPHxEZyQeDZ5NTfz2EM7zJuJS2YYTVptbrflTAGALU`
      }
    });

    return next.handle(request);
  }

  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();


    formData.append('files', file);
    formData.append('id','1');

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.baseUrl}/files`);
  }
}
