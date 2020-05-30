import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request/request.service';
import { VisitRequest } from '../model/request';
import { MatDialogRef, MatDialog } from '@angular/material';
import { AlertService } from '../alert';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { Router } from '@angular/router';
import { NotificationsDialogService } from '../dialog-content/notifications-dialog.service';

@Component({
  selector: 'app-process-requests',
  templateUrl: './process-requests.component.html',
  styleUrls: ['./process-requests.component.scss']
})
export class ProcessRequestsComponent implements OnInit {

  public reqTitle:string = "Requests information"
  public sideBarTitle = "Request list";

  public requests;
  
  public haveLoadedRequests:boolean = false;
  public shouldDisplayRequestInformation:boolean = false;

  
  public displayedReq:VisitRequest;

  public showDiagram:boolean = false;

  checkboxes: any[] = [
    { name: 'cb1', value: 'Calendar Compatbility', checked: false },
  ]

  checkboxes2: any[] = [
    { name: 'cb1', value: 'Calendar Compatbility', checked: false },
  ]

  optionss = {
    autoClose: true,
    keepAfterRouteChange: false
  };

  // google chart properties 
  // for animal - person compatibility
 
  title = 'Compatibility results:'
  type = 'ComboChart';
  data = [
     ["Common Interests", 8],
     ["Free time",4],
     ["Animal lover", 4],
     ["Friendly", 5],
  ];
   columnNames = ['Things in common', 'Results'];
   options = {   
      seriesType: 'bars',
      series: {1: {type: 'line'}}
   };
   width = 300;
   height = 250;

  constructor(private requestService: RequestService, private dialog:MatDialog, private alert: AlertService,
    private confirmationDialogService: ConfirmationDialogService, private route:Router,
    private notificationsDialogService:NotificationsDialogService) { 
    
  }

  CheckAllOptions() { //calendar compatibility
    if (this.checkboxes.every(val => val.checked == true))
      this.checkboxes.forEach(val => { val.checked = false });
    else
      this.checkboxes.forEach(val => { val.checked = true });
  }

  animalPersonCompatibility() { //check
    if (this.checkboxes2.every(val => val.checked == true))
      this.checkboxes2.forEach(val => { val.checked = false });
    else
      this.checkboxes2.forEach(val => { val.checked = true });

      this.showDiagram = true;
      

  }

  ngOnInit() {
    window.location.reload;
     this.getRequests();
  }

  getRequests(){

    this.requestService.getRequests().subscribe(req => { 
      console.log(req);
      this.requests = req;
      this.haveLoadedRequests = true;
    });
    console.log(this.requests);
  }



  displayRequestInformation(req){
    this.shouldDisplayRequestInformation = true;
   
    this.displayedReq = req;
  }

  openNofitificationsModal(){
    this.notificationsDialogService.notify()
    .then(()=> window.location.reload)
  
  }

  approveReq(){
    this.displayedReq.visitRequestAnswer = "approved";
    this.requestService.answerRequest(this.displayedReq).then(result => 

    this.confirmationDialogService.confirm('Please confirm..', 'Do you want to approve this request?')
      .then((isConfirmed) => {
          window.location.reload;
      
      }).then(()=>window.location.reload)
    )
  }

  rejectReq(){
    this.displayedReq.visitRequestAnswer = "rejected";
    this.requestService.answerRequest(this.displayedReq).then(req => 
    this.confirmationDialogService.confirm('Please confirm..', 'Do you want to reject this request?')
      .then((isConfirmed) => {
          window.location.reload;
        
      }).then(()=> window.location.reload)
    )
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
