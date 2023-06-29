import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DatingService } from './dating.service';

@Component({
  selector: 'app-dater',
  templateUrl: './dater.component.html',
  styleUrls: ['./dater.component.css']
})
export class DaterComponent {
  title:string='Component Name'
  constructor(private _cookieService:CookieService,private _router:Router,private _daterService:DatingService){
    
  }
  logout(){
    this._cookieService.delete('username');
    this._cookieService.delete('token');
    this._router.navigateByUrl('/login');
  }

  delete(){
    var pDto={
      username:this._cookieService.delete('username')
    }
    this._daterService.deleteProfile(pDto).subscribe(
      (response)=>{
        console.log(response);
        
      },
      (error)=>{
        console.log(error);
      }
    );
  }
}
