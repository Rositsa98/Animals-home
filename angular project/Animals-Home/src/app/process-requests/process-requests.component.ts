import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request/request.service';

@Component({
  selector: 'app-process-requests',
  templateUrl: './process-requests.component.html',
  styleUrls: ['./process-requests.component.scss']
})
export class ProcessRequestsComponent implements OnInit {

  public title:string = "Process requests"

  public sideBarTitle = "Requests";

  public requests;

  constructor(private requestService: RequestService) { }

  ngOnInit() {
    this.getRequests();
  }

  getRequests(){
    this.requestService.getRequests().then(req => this.requests = this.requests);
    console.log(this.requests);
  }

}
