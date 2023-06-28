import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {
    path:'',
    loadChildren:()=>import('./authentication/auth.module').then(m=>m.AuthModule)
  },
  {
    path:'dating',
    loadChildren:()=>import('./dater/dating.module').then(m=>m.DatingModule),
    // canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
