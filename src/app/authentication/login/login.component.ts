
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { filter, Subject, take, takeUntil } from 'rxjs';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  public loginValid = true;
  public username = '';
  public password = '';
  public processing:boolean=false;

  private _destroySub$ = new Subject<void>();
  private readonly returnUrl: string;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _authService: AuthService,
    private _toastrService:ToastrService
  ) {
    this.returnUrl = this._route.snapshot.queryParams['returnUrl'] || '/dating';
  }

  public ngOnInit(): void {
    this._authService.isAuthenticated$.pipe(
      filter((isAuthenticated: boolean) => isAuthenticated),
      takeUntil(this._destroySub$)
    ).subscribe( _ => this._router.navigateByUrl(this.returnUrl));
  }

  public ngOnDestroy(): void {
    this._destroySub$.next();
  }

  public onSubmit(): void {
    this.processing=true;
    this.loginValid = true;
    var loginRequest={
      username:this.username,
      password:this.password
    }
    this._authService.login(loginRequest).subscribe(
      (response)=>{
        console.log(response);
        
        this.loginValid = true;
        this.processing=false
        this._router.navigateByUrl('/dating');
      },
      (error)=>{
        this.processing=false
        console.log(error);
        this._toastrService.error("Something went wrong, try again!");
      }
    );
  }
}