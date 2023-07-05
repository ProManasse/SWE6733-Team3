import { Component, OnInit, Input } from '@angular/core';
import { trigger, keyframes, animate, transition } from "@angular/animations";
import * as kf from './keyframes';
import { Subject } from 'rxjs';
import { User } from './user';
import  data from './users.json'
 
@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css'],
  animations: [
    trigger('cardAnimator', [
      transition('* => swiperight', animate(750, keyframes(kf.swiperight))),
      transition('* => swipeleft', animate(750, keyframes(kf.swipeleft)))
    ])
  ]
})
export class CardComponent {
  public users: any = data;
  public index = 0;
  @Input()
  parentSubject?: Subject<any>;



  animationState?: string;
  constructor() { }

  ngOnInit() {
    this.parentSubject?.subscribe(event => {
      this.startAnimation(event)
    });
  }

  startAnimation(state:any) {
    if (!this.animationState) {
      this.animationState = state;
    }

    if(state=='swipeleft'){
      console.log(state+" I hate "+this.users[this.index].name);
      
    }else if(state=='swiperight'){
      console.log(state+" I love "+this.users[this.index].name);
      
    }
  }

  resetAnimationState(state:any) {
    this.animationState = '';
    this.index++;
  }


  ngOnDestroy() {
    this.parentSubject?.unsubscribe();
  }

}
