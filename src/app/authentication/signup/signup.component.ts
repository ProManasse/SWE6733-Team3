import { Component, Inject} from '@angular/core';
import { AuthService } from '../auth.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import {MatDialog, MAT_DIALOG_DATA, MatDialogRef, MatDialogModule} from '@angular/material/dialog';
import {NgIf} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {FormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
export interface DialogData {
  username: string;
}


@Component({
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'username.html',
  standalone: true,
  imports: [MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule, MatButtonModule],
})
export class DialogOverviewExampleDialog {
  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData,
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  public firstName='';
  public lastName='';
  public password='';
  public retype='';
  public processing:boolean=false;

  constructor(private _authService:AuthService,private _toastr:ToastrService,private _router:Router,public dialog: MatDialog){

  }
  onSubmit(){
    this.processing=true;
    var username=this.firstName.substring(0, 2).toLowerCase()+this.lastName.substring(0, 4).toLowerCase();
    var signupRequest={
        username:username,
        password:this.password
      }
    this._authService.signup(signupRequest).subscribe(
      (response)=>{
        this.processing=false
        this._toastr.success("Account Created Successfully");
        this.openDialog(username);
      },
      (error)=>{
        this.processing=false
        this._toastr.error(error.error.message);
      }
    );
    
  }
  openDialog(username:string): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      data: {username:username},
    });

    dialogRef.afterClosed().subscribe(result => {
      this._router.navigateByUrl('/login');
    });
  }


}
