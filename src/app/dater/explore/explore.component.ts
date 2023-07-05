import { Component } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-explore',
  templateUrl: './explore.component.html',
  styleUrls: ['./explore.component.css']
})
export class ExploreComponent {
  parentSubject:Subject<string> = new Subject();

  constructor() {

  }

 cardAnimation(value:any) {
    this.parentSubject.next(value);
  }
}
