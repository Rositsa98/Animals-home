import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request/request.service';
import { VisitRequest } from '../model/request';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { SendRequestDialogComponent } from '../send-request-dialog/send-request-dialog.component';

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

  confirmSendReqDialogRef: MatDialogRef<SendRequestDialogComponent>;

  constructor(private requestService:RequestService, private dialog:MatDialog) { }

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
    this.confirmSendReqDialogRef = this.dialog.open(SendRequestDialogComponent);

  }
}
