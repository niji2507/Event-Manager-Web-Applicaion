import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from './../service/authentication.service';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm :FormGroup;

  invalidLogin = false;
  errorMessage = "Invalid Credentials - Either user name or password is invalid (or) You are not Registered!! Please Sign up in case of First login";
  constructor(private router: Router,
              private authenticateService : AuthenticationService,
              private formBuider : FormBuilder) { 
                this.loginForm = this.formBuider.group({
                  username : ['', Validators.required],
                  password : ['', Validators.required]
                })
              }

  ngOnInit() {
  }
  doLogin(){
    //redirect to dashboard page
    this.authenticateService.aunthenticate(this.loginForm.get('username').value,this.loginForm.get('password').value).subscribe(
      data => {
        this.router.navigate(['dashboard']);
        this.invalidLogin =false;
      },
      error => {
        this.invalidLogin = true;
      }
    )
  }
  
  registerUser() {
    this.router.navigate(['register']);
  }

}
