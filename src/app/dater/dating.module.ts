import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DatingRoutingModule } from './dating-routing.module';
import { HomeComponent } from './home/home.component';
import { ExploreComponent } from './explore/explore.component';
import { ChatComponent } from './chat/chat.component';
import { ProfileComponent } from './profile/profile.component';
import { DaterComponent } from './dater.component';
import { MaterialModule } from '../material.module';
import { AvatarModule } from 'ngx-avatars';
import { UploadImagesComponent } from './upload-images/upload-images.component';
import {  NgbModule } from '@ng-bootstrap/ng-bootstrap';



@NgModule({
  declarations: [
    HomeComponent,
    ExploreComponent,
    ChatComponent,
    ProfileComponent,
    DaterComponent,
    UploadImagesComponent
  ],
  imports: [
    CommonModule,
    DatingRoutingModule,
    MaterialModule,
    AvatarModule,
    NgbModule
  ]
})
export class DatingModule { }
