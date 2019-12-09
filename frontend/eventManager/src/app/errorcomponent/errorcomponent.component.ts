import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-errorcomponent',
  templateUrl: './errorcomponent.component.html',
  styleUrls: ['./errorcomponent.component.css']
})
export class ErrorcomponentComponent implements OnInit {
  errormessage = "error occurred !!. The page you are trying to access doesn't exists"
  constructor() { }

  ngOnInit() {
  }

}
