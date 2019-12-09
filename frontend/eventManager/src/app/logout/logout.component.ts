import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from './../service/authentication.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(private authenticateService : AuthenticationService) { }

  ngOnInit() {
    this.authenticateService.logout();
  }

}
