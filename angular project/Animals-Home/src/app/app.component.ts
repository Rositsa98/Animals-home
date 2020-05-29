import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { NotificationsDialogComponent } from './notifications-dialog/notifications-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'Animals-Home';


  public isUserLoggedIn = (localStorage.getItem("token") != null);

  ngOnInit(){
    this.isUserLoggedIn = (localStorage.getItem("token") != null) &&
                      !( window.location.href.includes("send-request") || 
                        window.location.href.includes("process-requests")||
                        window.location.href.includes("chat"));
  }


}
