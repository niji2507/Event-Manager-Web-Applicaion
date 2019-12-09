import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent } from './login/login.component';
import { DashboardComponent } from'./dashboard/dashboard.component';
import {ErrorcomponentComponent} from './errorcomponent/errorcomponent.component';
import {EventListComponent} from './event-list/event-list.component';
import {LogoutComponent} from './logout/logout.component';
import {AuthGuardService} from './service/auth-guard.service';
import {RegistrationComponent} from './registration/registration.component';
import  {NotificationComponent} from './notification/notification.component';
const routes: Routes = [
  { path :'',component:LoginComponent},
  { path :'login',component:LoginComponent},
  { path :'dashboard',component:DashboardComponent, canActivate:[AuthGuardService]},
  { path :'eventList',component:EventListComponent,canActivate:[AuthGuardService]},
  { path :'logout',component:LogoutComponent,canActivate:[AuthGuardService]},
  {path : 'register',component:RegistrationComponent},
  {path : 'notification',component:NotificationComponent,canActivate:[AuthGuardService]},
  { path :'**',component:ErrorcomponentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
