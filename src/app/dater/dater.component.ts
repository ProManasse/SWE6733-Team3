import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { DatingService } from './dating.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-dater',
  templateUrl: './dater.component.html',
  styleUrls: ['./dater.component.css']
})
export class DaterComponent {
  title:string='Component Name'
  constructor(
    private _cookieService:CookieService,
    private _router:Router,
    private _daterService:DatingService,
    private _toastr:ToastrService){
    
  }
  logout(){
    this._cookieService.delete('username');
    this._cookieService.delete('token');
    this._router.navigateByUrl('/login');
  }

  delete(){
    var pDto={
      username:this._cookieService.get('username')
    }
    this._daterService.deleteProfile(pDto).subscribe(
      (response:any)=>{
        this._toastr.success(response.message);
        this.logout();
        
      },
      (error)=>{
        this._toastr.error("Something went wrong, please try again!");
      }
    );
  }

  show(){
    this._router.navigateByUrl('/dating/profileview');
  }

}
