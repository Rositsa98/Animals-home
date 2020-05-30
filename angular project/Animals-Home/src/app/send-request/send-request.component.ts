import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request/request.service';
import { VisitRequest } from '../model/request';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { SendRequestDialogComponent } from '../send-request-dialog/send-request-dialog.component';
import { Router } from '@angular/router';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { NotificationsDialogService } from '../dialog-content/notifications-dialog.service';

@Component({
  selector: 'app-send-request',
  templateUrl: './send-request.component.html',
  styleUrls: ['./send-request.component.scss']
})
export class SendRequestComponent implements OnInit {

  private visitRequest:VisitRequest;

  public chosenDate:string;
  public month:string = "June ";
  public year: string = " 2020";

  public isMonthJune:boolean = true;
  public isMonthJuly:boolean = false;

  constructor(private requestService:RequestService, private dialog:MatDialog, private route: Router,
    private confirmationDialogService:ConfirmationDialogService,
    private notificationsDialogService:NotificationsDialogService) { }

  ngOnInit(): void {
    this.visitRequest = {
      petName : "pet",
      userName: localStorage.getItem("username"),
      shelterName:"shelter",
      date:"",
      visitRequestAnswer:"NOTDEFINED",
            
    };
  }

  sendRequest(){
    this.requestService.sendRequest(this.visitRequest).then(result =>{
      if(result != null && result.visitRequestAnswer === "not defined"){
        console.log("Success");
      }else{
        console.log("Rejected");
      }
    }).catch(err => console.log(err));
  }

  choose(day){
    this.chosenDate = day + this.month + this.year;
    this.visitRequest.date = day + "/" +"06" + "/" + this.year
  }

  changeToPrevMonth(){
    this.isMonthJune = true;
    this.isMonthJuly = false;
    this.month = "June "
    window.location.reload;
  }

  changeToNextMonth(){
    this.isMonthJune = false;
    this.isMonthJuly = true;
    this.month = "July "
    window.location.reload;
  }

  openConfirmModal(){
    this.sendRequest();
    this.confirmationDialogService.confirm('Request is sent!', 'You have successfully sent the request.')
        .then((isConfirmed) => {
            window.location.reload;
          
        }).then(()=> window.location.reload)
      ;

  }

  openNofitificationsModal(){
    this.notificationsDialogService.notify()
    .then(()=> window.location.reload)
  
  }

  logout() {

    if (localStorage.getItem("token") != null) {
      localStorage.removeItem("token");

      if (localStorage.getItem("shelterName") != null) {
        localStorage.removeItem("shelterName");
      } else if (localStorage.getItem("username") != null) {
        localStorage.removeItem("username");
      }

      this.route.navigate(['/login']).then(() => window.location.reload());

    } 

  }
}
