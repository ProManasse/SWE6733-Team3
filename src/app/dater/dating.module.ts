import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DatingRoutingModule } from './dating-routing.module';
import { HomeComponent } from './home/home.component';
import { ExploreComponent } from './explore/explore.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
  declarations: [
    HomeComponent,
    ExploreComponent,
    ChatComponent,
    ProfileComponent
  ],
  imports: [
    CommonModule,
    DatingRoutingModule
  ]
})
export class DatingModule { }
