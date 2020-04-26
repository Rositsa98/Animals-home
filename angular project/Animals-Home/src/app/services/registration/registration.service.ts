import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { User } from '../../model/user';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  constructor(private http: HttpClient) { }

  registerUser(user:User):Promise<boolean>{

    var username = user.username;
    var password = user.password;
    var firstName = user.firstName;
    var lastName = user.lastName;
    var phoneNumber = user.phoneNumber;
    var roles = user.roles;
    var email = user.email;
    var imageUrls = user.imageUrls;
    var address = user.address;
    var description = user.description;
    var shelterCode = user.shelterCode;
    var active = true;

    const body = { username, password, firstName, lastName, phoneNumber, roles, email, imageUrls, address, description, shelterCode, active};
    const registerUrl = '/clientServer/api/user/registerUser';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    console.log(body);

    var redirectUrl = null;

    return this.http.post<any>(registerUrl, body, {
      headers,
    }).toPromise().then(result => { console.log(result); return true;})
    .catch((err: HttpErrorResponse) => {
      console.error('An error occurred:', err.error);
      return false;
    });
  }

}
