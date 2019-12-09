import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientModule, HTTP_INTERCEPTORS} from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ErrorcomponentComponent } from './errorcomponent/errorcomponent.component';
import { EventListComponent } from './event-list/event-list.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { LogoutComponent } from './logout/logout.component';
import { RegistrationComponent } from './registration/registration.component';
import {HttpInterceptorBasicAuthService} from './service/http/http-interceptor-basic-auth.service';
import { NotificationComponent } from './notification/notification.component'


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    ErrorcomponentComponent,
    EventListComponent,
    HeaderComponent,
    FooterComponent,
    LogoutComponent,
    RegistrationComponent,
    NotificationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    {
     provide : HTTP_INTERCEPTORS,
     useClass : HttpInterceptorBasicAuthService,
     multi :true 
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
