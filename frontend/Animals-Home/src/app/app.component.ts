import { Component, OnInit } from '@angular/core';
import { NotificationsService } from './services/notifications/notifications.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Animals-Home';
  // public isUserLoggedIn = (localStorage.getItem("token") != null);
  constructor(public notificationsService: NotificationsService) {}

  ngOnInit() {
    // this.isUserLoggedIn = (localStorage.getItem("token") != null) &&
    //                   !( window.location.href.includes("send-request") || 
    //                     window.location.href.includes("process-requests"));
    this.notificationsService.startConnection();
    this.notificationsService.initializeEventListeners();
  }
}
