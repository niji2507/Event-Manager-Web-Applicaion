import { Component, OnInit } from '@angular/core';
import {UserDataService} from './../service/data/user-data.service';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {EventList} from './../schemas/eventschema';
import { AuthenticationService } from './../service/authentication.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  eventList: EventList[];
  eventCreationForm : FormGroup;
  payload : any;
  showFeedback : boolean;
  message : string;
  constructor(private userDataService: UserDataService,
  private formBuilder : FormBuilder,
private authenticationService : AuthenticationService,
private router :Router) { 
    this.eventCreationForm = this.formBuilder.group({
                  eventname : ['', Validators.required],
                  description : ['', Validators.required],
                  duration : [''],
                  location : ['', Validators.required],
                  tags : [''],
                  fees : ['', Validators.required],
                  maxparticipants : ['', Validators.required]
                })
  }
  
  ngOnInit() {
    this.userDataService.getListOfUserEvents().subscribe(
      data=> {
        this.eventList = data;
      }
    )
  }

  navigateToList() {
    this.router.navigate(['eventList']);
  }
  get f() { return this.eventCreationForm.controls; }

  createEventPayload(){
    this.payload = {};
    this.payload['eventName'] = this.eventCreationForm.controls['eventname'].value;
    this.payload['description'] = this.eventCreationForm.controls['description'].value;
    this.payload['duration'] = this.eventCreationForm.controls['duration'].value;
    this.payload['location'] = this.eventCreationForm.controls['location'].value;
    this.payload['fees'] = this.eventCreationForm.controls['fees'].value;
    this.payload['tags'] = this.eventCreationForm.controls['tags'].value;
    this.payload['maxParticipants'] = this.eventCreationForm.controls['maxparticipants'].value;
    this.payload['createdBy'] = this.authenticationService.getLoggedInUserName();
  }
  confirmCreation() { 
    this.createEventPayload();  
    this.userDataService.createEvent(this.payload).subscribe(
      response => {
        this.eventCreationForm.reset();
        this.message = "Created event successfully",
        this.showFeedback= true;
        setTimeout(() => {
          this.showFeedback = false;
        }, 2000);
      }
    )
    
  }
  cancel() {
     this.eventCreationForm.reset();
  }
  handleSuceess(response){

  }
}
