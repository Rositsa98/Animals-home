import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { RequestService } from 'src/app/services/request/request.service';

@Component({
  selector: 'app-notifications-dialog',
  templateUrl: './notifications-dialog.component.html',
  styleUrls: ['./notifications-dialog.component.scss']
})
export class NotificationsDialogComponent implements OnInit {

  public notifications; 
  public haveLoadedNotifications = false;
  
  constructor(private activeModal: NgbActiveModal, private requestService:RequestService) { }

  ngOnInit() {
    this.requestService.getNotifications().subscribe(notif => { 
      console.log(notif);
      this.notifications = notif;
      this.haveLoadedNotifications = true;
    });
  }

  public decline() {
    this.activeModal.close(false);
  }

  public accept() {
    this.activeModal.close(true);
  }

  public dismiss() {
    this.activeModal.dismiss();
  }

}
