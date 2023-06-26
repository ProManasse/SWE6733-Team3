import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { filter, Subject, take, takeUntil } from 'rxjs';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  public loginValid = true;
  accountId: string = '';
  formdata: any;
  showModal: boolean = false;
  user: any = '';

  private _destroySub$ = new Subject<void>();
  private readonly returnUrl: string;

  constructor(
    private _route: ActivatedRoute,
    private _router: Router,
    private _authService: AuthService,
    private cookieService: CookieService,
    private _toastr: ToastrService
  ) {
    this.returnUrl = this._route.snapshot.queryParams['returnUrl'] || '/game';
  }

  public ngOnInit(): void {
    this.formdata = new FormGroup({
      email: new FormControl("", Validators.compose([
        Validators.required,
        Validators.min(1)
        //  Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")
      ])),
      password: new FormControl("", Validators.compose([
        Validators.required,
        Validators.min(1)
      ]))
    });


  }

  public ngOnDestroy(): void {
    this._destroySub$.next();
  }

  hide = true;
  // get emailInput() { return this.formdata.get('email'); }
  // get passwordInput() { return this.formdata.get('password'); } 

  public onSubmit(loginDto: any) {
    this.showModal = true
    this.loginValid = true;
    var user = {
      userName: loginDto.email,
      password: loginDto.password
    }
    // this._authService.login(user).pipe(
    //   take(1)
    // ).subscribe(
    //   (response) => {
    //     this.user = response;
    //     console.log(this.user);
    //     if(this.user.access=="ON"){
    //       sessionStorage.setItem("access", this.user.access);
    //       this.direct(this.user);
    //     }else{
    //       this._toastr.success("You're no longer have access","Access Restricted");
    //     }
    //   },
    //   (e) => {
    //     this.showModal = false;
    //     this._toastr.error(e.error.message, "Failed");
    //   }

    // );
  }

  public direct(u: any) {
    this.cookieService.set("nid", u.nid);
    if (u.roles.length == 1) {
      if (u.roles[0] == 'ADMIN') {
        sessionStorage.setItem("userType","ADMIN");
        this._router.navigateByUrl('/admin');
      } else if (u.roles[0] == 'MASTER') {
        sessionStorage.setItem("userType","MASTER");
        this._toastr.info("Master");
        this._router.navigateByUrl('/master');
      } else if (u.roles[0] == 'ACCOUNTANT') {
        sessionStorage.setItem("userType","ACCOUNTANT");
        this.cookieService.set("userType","ACCOUNTANT");
        this.verifyEmployee(u);
      } else if (u.roles[0] == 'TEACHER') {
        sessionStorage.setItem("userType","TEACHER");
        this.cookieService.set("userType","TEACHER");
        this._router.navigateByUrl('/teacher');
      } else if (u.roles[0] == 'PARENT') {
        sessionStorage.setItem("userType","PARENT");
        this._router.navigateByUrl('/parent');
      }
    } else if (u.roles.length > 1) {
      //Pop up Options
    } else if (u.roles.length == 0) {
      this._toastr.info("User does not have any permission", "Permission Required");
    }
  }

  verifyEmployee(u: any) {
    console.log("User: "+u.nid);
    
    // this._authService.getEmpDetails(u).subscribe(
    //   (response:any) => {
    //     console.log(response);
    //     this.cookieService.set("branch",response.job.branch.name);
    //     this._router.navigateByUrl('/accountant');
    //   },
    //   (error) => {
    //     console.log("No Details: " + error);

    //   }
    // );
  }
}

