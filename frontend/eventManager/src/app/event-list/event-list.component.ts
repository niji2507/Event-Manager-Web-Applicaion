import { Component, OnInit } from '@angular/core';
import { UserDataService } from './../service/data/user-data.service';
import { AuthenticationService } from './../service/authentication.service';
import { FormGroup, FormControl } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Validators } from '@angular/forms';
import {Router} from '@angular/router';
import {EventList} from './../schemas/eventschema';
declare var $: any;

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  
  eventList: EventList[];
  eventDetails :any;
  index : number;
  eventModificationForm : FormGroup;
  message :string;
  registrationMessage : string;
  showFeedback :boolean;
  userList :  any[];
  constructor(private userDataService : UserDataService,
              private authenticationService: AuthenticationService,
              private formBuilder :  FormBuilder,
            private router: Router) { 
      this.eventDetails = {};
      this.eventModificationForm = this.formBuilder.group({
                  eventname : ['', Validators.required],
                  description : ['', Validators.required],
                  duration : [''],
                  location : ['', Validators.required],
                  tags : [''],
                  fees : ['', Validators.required],
                  maxparticipants : ['', Validators.required]
      }),
      this.showFeedback = false;
  }

  ngOnInit() {
    this.refreshEvents()
  }

  refreshEvents() {
    this.userDataService.getEventList().subscribe(
      response=> {
        this.eventList = response;
      }
    )
  }
  showUserDetails(event) {
    this.userList = event.userList;
    $("#showUserDetails").modal('show');
  }
  get f() { return this.eventModificationForm.controls; }

  isCreatedUser(username){
    if(this.authenticationService.getLoggedInUserName() === username) {
      return true;
    }
    return false;
  }

  showEditForm(event,index){
    this.eventDetails = event;
    this.index = index;
    this.eventModificationForm.controls['eventname'].setValue(this.eventDetails.eventName);
    this.eventModificationForm.controls['description'].setValue(this.eventDetails.description);
    this.eventModificationForm.controls['duration'].setValue(this.eventDetails.duration);
    this.eventModificationForm.controls['location'].setValue(this.eventDetails.location);
    this.eventModificationForm.controls['fees'].setValue(this.eventDetails.fees);
    this.eventModificationForm.controls['tags'].setValue(this.eventDetails.tags);
    this.eventModificationForm.controls['maxparticipants'].setValue(this.eventDetails.maxParticipants);    
    $("#editEventModal").modal('show');
  }

  showEventDetails(event) {
    this.eventDetails = event;
     $("#showEventModal").modal('show');
  }

  ModifyEvent() {
    this.eventDetails.eventName = this.eventModificationForm.controls['eventname'].value;
    this.eventDetails.description = this.eventModificationForm.controls['description'].value;
    this.eventDetails.duration = this.eventModificationForm.controls['duration'].value;
    this.eventDetails.location = this.eventModificationForm.controls['location'].value;
    this.eventDetails.fees = this.eventModificationForm.controls['fees'].value;
    this.eventDetails.tags = this.eventModificationForm.controls['tags'].value;
    this.eventDetails.maxParticipants = this.eventModificationForm.controls['maxparticipants'].value;
    this.userDataService.updateEvent(this.eventDetails.eventId,this.eventDetails).subscribe(
      response => {
        this.refreshEvents();
      }
    )
  }

  participateInEvent(event) {
    this.userDataService.registerToEvent(event.eventId).subscribe(
      response => {
        this.registrationMessage = "Participation done right !!",
        this.showFeedback= true;
        this.refreshEvents();
        setTimeout(() => {
          this.showFeedback = false;
        }, 2000);
      },
      error =>{
        this.registrationMessage = "error in participating !!",
        this.showFeedback= true;
        setTimeout(() => {
          this.showFeedback = false;
        }, 2000);
      }
    )
  }

  addEvent() {
    this.router.navigate(['dashboard']);
  }
  
  DeleteEvent() {
    this.userDataService.deleteEvent(this.eventDetails.eventId).subscribe(
      response =>{
       // this.message ="success";
       this.refreshEvents();
      },
      error =>{
        console.log(error);
      }
    )
  }

}
