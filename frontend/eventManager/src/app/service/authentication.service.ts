import { Injectable } from '@angular/core';
import {HttpHeaders,HttpClient} from '@angular/common/http';
import {Authenticationbean} from '../schemas/AuthenticationBean';
import {map} from 'rxjs/operators';
import {UserSchema} from '../schemas/UserSchema';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http:HttpClient) { }

  aunthenticate(username, password) {
    let BasicAuthString = 'Basic ' + window.btoa(username + ':' + password);
    let headers = new HttpHeaders({
      Authorization: BasicAuthString
    })
    return this.http.get<Authenticationbean>('http://localhost:8080/basicauth',
      { headers }).pipe(
      map(
        data => {
          sessionStorage.setItem("aunthenticatedUser", username);
          sessionStorage.setItem("token", BasicAuthString);
          return data;
        })
      );
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem('aunthenticatedUser');
  }

  getAuthenticatedToken(){
    if(this.getAuthenticatedUser()) 
      return sessionStorage.getItem('token');
  }
  isUserLoggedIn(){
    let user = this.getAuthenticatedUser();
    let token = this.getAuthenticatedToken();
    if(user &&  token){
      return true;
    }
    return false;
  }

  getLoggedInUserName() {
    return this.getAuthenticatedUser();
  }

  registerUser(user) {
    return this.http.post('http://localhost:8080/register',user);
  }
  isUseralreadyExists(username) {
    if(username === "niji") {
      return true;
    }
    return false;
  }

  logout() {
    sessionStorage.removeItem('aunthenticatedUser');
    sessionStorage.removeItem('token')
  }
}
