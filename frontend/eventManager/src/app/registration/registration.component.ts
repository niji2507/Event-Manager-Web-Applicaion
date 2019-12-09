import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from './../service/authentication.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registrationForm : FormGroup;
  userAlreadyExists : boolean = false;
  User: any;
  errorMessage = "Username Already exists!! Please provide a different name :)";
  constructor(private router: Router,
              private authenticateService : AuthenticationService,
              private formBuider : FormBuilder) { 
                this.registrationForm = this.formBuider.group({
                  firstname : ['', Validators.required],
                  lastname : ['', Validators.required],
                  username : ['', Validators.required],
                  password : ['', Validators.required],
                  email : ['', Validators.required],
                  contactNum :['', Validators.required]
                }),
                this.User = {}
              }

  ngOnInit() {
  }
  get f() { return this.registrationForm.controls; }

  confirmRegistration() {
    this.User = {
      "userName" : this.registrationForm.get('username').value,
      "password" : this.registrationForm.get('password').value,
      "firstName" : this.registrationForm.get('firstname').value,
      "lastName": this.registrationForm.get('lastname').value,
      "email" : this.registrationForm.get('email').value,
      "contactNum" : this.registrationForm.get('contactNum').value
    }
    this.authenticateService.registerUser(this.User).subscribe (
      data => {
        console.log(data);
        this.userAlreadyExists = false;
        this.router.navigate(['login']);
      },
      error=>{
        this.userAlreadyExists = true;
        setTimeout(() => {
          this.userAlreadyExists = true;
        }, 3000);
      }
    )   
  }
  cancel(){
    this.router.navigate(['login']);
  }

}
