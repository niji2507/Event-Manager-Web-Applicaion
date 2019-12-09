import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from '@angular/common/http';
import {EventList} from '../../schemas/eventschema'
import {AuthenticationService} from '../../service/authentication.service';
import  {NotificationList} from '../../schemas/notificationSchema';
@Injectable({
  providedIn: 'root'
})
export class UserDataService {
  username : string;
  constructor(
    private http: HttpClient,
    private authenticationService : AuthenticationService
  ) { 
    
  }

 getEventList(){
    return this.http.get<EventList[]>('http://localhost:8080/event-list/' +this.authenticationService.getAuthenticatedUser() );
  }

  deleteEvent(id) {
    return this.http.delete('http://localhost:8080/event-list/' + this.authenticationService.getAuthenticatedUser() + '/' +  id);
  }

  getEventById(id) {
    return this.http.get<EventList>('http://localhost:8080/event-list/' + this.authenticationService.getAuthenticatedUser() + '/' + id);
  }
  updateEvent(id,event) {
    return this.http.put('http://localhost:8080/event-list/'+ this.authenticationService.getAuthenticatedUser() + '/' +id,event);
  }

  createEvent(event) {
    return this.http.post('http://localhost:8080/event-list/'+ this.authenticationService.getAuthenticatedUser() ,event);
  }

  registerToEvent(eventId){
    let payload = {
      "eventId" : eventId,
      "username" : this.authenticationService.getAuthenticatedUser()
    };
    return this.http.post('http://localhost:8080/event/register',payload);

  }

  getListOfUserEvents() {
    return this.http.get<EventList[]>('http://localhost:8080/userEventList/'+this.authenticationService.getAuthenticatedUser());
  }

  getListOfNotifications() {
    return this.http.get<NotificationList[]>('http://localhost:8080/notifications/' + this.authenticationService.getAuthenticatedUser());
  }

  deleteNotification(notificationId) {
    return this.http.delete('http://localhost:8080/notification/' + this.authenticationService.getAuthenticatedUser() + '/' + notificationId);
  }
  
}
