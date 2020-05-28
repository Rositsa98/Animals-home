import { Component, OnInit } from '@angular/core';
import { RequestService } from '../services/request/request.service';
import { VisitRequest } from '../model/request';

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

  //google chart properties 
  //for animal - person compatibility
 
  // public line_ChartData = [
  //   ['Year', 'Sales', 'Expenses'],
  //   ['2004', 1000, 400],
  //   ['2005', 1170, 460],
  //   ['2006', 660, 1120],
  //   ['2007', 1030, 540]];

  //   public line_ChartOptions = {
  //     title: 'Company Performance',
  //     curveType: 'function',
  //     legend: {
  //         position: 'bottom'
  //     }
  // };

  constructor(private requestService: RequestService) { }

  ngOnInit() {
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

}
