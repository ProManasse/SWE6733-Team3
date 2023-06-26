import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DaterComponent } from './dater.component';
import { HomeComponent } from './home/home.component';
import { ExploreComponent } from './explore/explore.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  {
    path:'',component:DaterComponent,
    children:[
      {path:'',component:HomeComponent},
      {path:'home',component:HomeComponent},
      {path:'explore',component:ExploreComponent},
      {path:'chat',component:ChatComponent},
      {path:'profile',component:ProfileComponent}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DatingRoutingModule { }
