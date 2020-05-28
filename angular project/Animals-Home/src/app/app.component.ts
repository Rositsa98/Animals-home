import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import {NotificationsDialogComponent} from './notifications-dialog/notifications-dialog.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Animals-Home';

  public isUserLoggedIn = (localStorage.getItem("token") != null);


  notifDialogRef: MatDialogRef<NotificationsDialogComponent>;

  constructor(private router:Router, private dialog:MatDialog){

  }
  
  logout() {
    if (localStorage.getItem("token") != null) {
      localStorage.removeItem("token");

      if (sessionStorage.getItem("shelterName") != null) {
        sessionStorage.removeItem("shelterName");
      } else if (sessionStorage.getItem("username") != null) {
        sessionStorage.removeItem("username");
      }

      this.router.navigateByUrl('/login').then(() => window.location.reload);

    } else{
      this.isUserLoggedIn = false;
    }

    window.location.reload;
  }

  openNofitificationsModal(){
    this.notifDialogRef = this.dialog.open(NotificationsDialogComponent);
  }

}
