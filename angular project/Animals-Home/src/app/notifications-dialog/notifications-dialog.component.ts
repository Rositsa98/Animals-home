import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request/request.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-notifications-dialog',
  templateUrl: './notifications-dialog.component.html',
  styleUrls: ['./notifications-dialog.component.scss']
})
export class NotificationsDialogComponent implements OnInit {

  public notifications; 
  public haveLoadedNotifications = false;


  constructor(private requestService:RequestService, private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<NotificationsDialogComponent>) { }

  ngOnInit(): void {
    this.requestService.getNotifications().subscribe(notif => { 
      console.log(notif);
      this.notifications = notif;
      this.haveLoadedNotifications = true;
    });
  }

}
