import {Component} from '@angular/core';
import { FormGroup, FormBuilder }  from '@angular/forms';
import { DatingService } from '../dating.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  constructor(private _formBuilder: FormBuilder,private _datingService:DatingService,private _cookies:CookieService) {}
  personalFormGroup: FormGroup = this._formBuilder.group({
    firstName: [''],
    lastName: [''],
    gender: [''],
    dob: [''],
    address:[''],
    phoneNo:['']
  });
  crowFormGroup: FormGroup = this._formBuilder.group({
    adventure: [''],
    skill: [''],
    behavior: ['']
  });
  photosFormGroup: FormGroup = this._formBuilder.group({photosCtrl: ['']});

  submit(){
    var profileDto={
      name:this.personalFormGroup.controls['firstName'].value,
      otherName:this.personalFormGroup.controls['lastName'].value,
      gender:this.personalFormGroup.controls['gender'].value,
      phoneNo:this.personalFormGroup.controls['phoneNo'].value,
      dob:this.personalFormGroup.controls['dob'].value,
      address:this.personalFormGroup.controls['address'].value,
      adventure:this.crowFormGroup.controls['adventure'].value,
      skill:this.crowFormGroup.controls['skill'].value,
      behavior:this.crowFormGroup.controls['behavior'].value,
      username:this._cookies.get('username')
    }
    this._datingService.createProfile(profileDto).subscribe(
      (response:any)=>{
        this._cookies.set('id',response.id);
        console.log(response);
      },
      (error)=>{
        console.log(error);
      }
    );

  }
  
}
