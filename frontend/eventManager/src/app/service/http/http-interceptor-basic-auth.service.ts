import { Injectable } from '@angular/core';
import {HttpInterceptor,HttpHandler,HttpRequest} from '@angular/common/http';
import {AuthenticationService} from '../authentication.service'

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorBasicAuthService implements HttpInterceptor {

  constructor(private authenticationService :AuthenticationService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler){
    let basicAuthToken = this.authenticationService.getAuthenticatedToken();
    let username = this.authenticationService.getAuthenticatedUser();
    if (basicAuthToken && username) {
      request = request.clone({
        setHeaders: {
          Authorization: basicAuthToken
        }
      })
    }
    return next.handle(request);
  }


}
