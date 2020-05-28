import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { VisitRequest } from 'src/app/model/request';

const httpOptionsShelter = {
  headers: new HttpHeaders({'Content-Type': 'application/json', 
  Authorization: 'Bearer ' + localStorage.getItem('token'),
  username: sessionStorage.getItem("shelterName") })
};

const httpOptionsUser = {
  headers: new HttpHeaders({'Content-Type': 'application/json', 
  Authorization: 'Bearer ' + localStorage.getItem('token'),
  username: localStorage.getItem("username")
 })
};

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  constructor(private http: HttpClient) { }

  getRequests() {
    const requestsUrl = '/clientServer/visit/getRequests';

    return this.http.get(requestsUrl, httpOptionsShelter);
  }

  getNotifications(){
    const requestsUrl = '/clientServer/api/user/getNotifications';

    return this.http.get(requestsUrl, httpOptionsShelter);
  }

  sendRequest(request: VisitRequest):Promise<VisitRequest>{

    var petName = request.petName;
    var userName = request.userName;
    var shelterName = request.shelterName;
    var visitRequestAnswer = request.visitRequestAnswer;
    var date = request.date;



    const body = {petName, userName, shelterName, visitRequestAnswer, date};
    const sendRequestUrl = "/clientServer/visit/sendRequest";
    const headers = new HttpHeaders({
      Authorization: 'Bearer ' + localStorage.getItem('token')
    });

    return this.http.post<VisitRequest>(sendRequestUrl, body, {
      headers,
    }).toPromise().then(result => { 
      console.log(body);
      console.log(result); return result;})
    .catch((err: HttpErrorResponse) => {
      console.error('An error occurred:', err.error);
      return null;
    });

  }

}
