import { Component, OnInit } from '@angular/core';
import { DatingService } from '../dating.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit{
  friends:any;
  constructor(private datingService:DatingService,private cookies:CookieService){

  }
  ngOnInit(): void {
    this.getFriends();
  }



  getFriends(){
    var dto={
      ownerId:'902'
    }
    this.datingService.getMyFriends(dto).subscribe(
      (response)=>{
        this.friends=response;
        console.log(response);
      },
      (error)=>{
        console.log(error);
        
      }
    );
  }
}
