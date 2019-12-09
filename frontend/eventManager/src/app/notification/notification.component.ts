import { Component, OnInit } from '@angular/core';
import {NotificationList} from './../schemas/notificationSchema';
import {UserDataService} from './../service/data/user-data.service';
import {EventList} from './../schemas/eventschema';
declare var $: any;
 
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {

  notificationList : NotificationList[];
  showFeedback : boolean =false;
  errorFeedback : boolean = false;
  errorMessage : any;
  successMessage : any;
  eventDetail : EventList;
  eventDetailShow : boolean=false;
  notificationsize : number;
  constructor(private userDataService: UserDataService) {
    this.errorMessage = "Error marking entry as Read";
    this.successMessage = "Read the notification and successfully removed entry !!"
   }

  ngOnInit() {
    this.getListOfNotifcation();
  }
  getListOfNotifcation() {
    this.userDataService.getListOfNotifications().subscribe (
     data =>{
       this.notificationList = data;
       this.notificationsize = data.length;

     },
      error=> {
        console.log(error);
      }
    )
  }

  markAsRead(notificationId){
    this.userDataService.deleteNotification(notificationId).subscribe(
      response => {
        this.showFeedback = true;
        this.getListOfNotifcation();
        setTimeout(() => {
          this.showFeedback = false;
        }, 2000);
      },
      error => {
        this.errorFeedback = true;
         setTimeout(() => {
          this.errorFeedback = false;
        }, 2000);
      }
    )
  }

  openEventsModal(id) {
    this.userDataService.getEventById(id).subscribe(
      data => {
        this.eventDetail = data;
        this.eventDetailShow = true;
        $("#showEventModal").modal('show');
      }
    )
  }

}
