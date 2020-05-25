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
    email, imageUrls, address, birthday, gender
  ): Promise<boolean> {

    var active = true;

    const body = {
      username, password, firstName, lastName, phoneNumber, roles, email,
      imageUrls, address, active, birthday, gender
    };
    const registerUrl = '/api/user/registerUser';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    console.log(body);

    var redirectUrl = null;

    return this.http.post<any>(registerUrl, body, {
      headers,
    }).toPromise().then(result => { console.log(result); return true; })
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
    var imageUrls = shelter.imageUrls;
    var address = shelter.address;
    var active = true;
    var description = shelter.description;
    var shelterCode = shelter.shelterCode;

    const body = { username, password, phoneNumber, roles, email, imageUrls, address, active, description, shelterCode };
    const registerUrl = '/api/user/registerShelter';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    console.log(body);

    var redirectUrl = null;

    return this.http.post<any>(registerUrl, body, {
      headers,
    }).toPromise().then(result => { console.log(result); return true; })
      .catch((err: HttpErrorResponse) => {
        console.error('An error occurred:', err.error);
        return false;
      });
  }
}
