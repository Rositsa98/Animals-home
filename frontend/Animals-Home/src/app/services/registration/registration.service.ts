import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Shelter } from 'src/app/model/shelter';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  registerUser(
    username, password, firstName, lastName, phoneNumber, roles,
    email, address, birthday, gender
  ): Promise<boolean> {

    var active = true;

    const body = {
      username, password, firstName, lastName, phoneNumber, roles, email,
       address, active, birthday, gender
    };
    const registerUrl = '/api/user/registerUser';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(registerUrl, body, {
      headers,
    }).toPromise().then(result => { return true; })
      .catch((err: HttpErrorResponse) => {
        console.error('An error occurred:', err.error);
        return false;
      });
  }

  registerShelter(shelter: Shelter): Promise<boolean> {
    var username = shelter.username;
    var password = shelter.password;
    var phoneNumber = shelter.phoneNumber;
    var roles = shelter.roles;
    var email = shelter.email;
    var address = shelter.address;
    var active = true;
    var description = shelter.description;
    var shelterCode = shelter.shelterCode;
    var workDay = shelter.workDay;

    const body = { username, password, phoneNumber, roles, email, address, active, description, shelterCode, workDay };
    const registerUrl = '/api/user/registerShelter';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    return this.http.post<any>(registerUrl, body, {
      headers,
    }).toPromise().then(result => { return true; })
      .catch((err: HttpErrorResponse) => {
        console.error('An error occurred:', err.error);
        return false;
      });
  }
}
