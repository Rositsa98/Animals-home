import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {
  }

  isAuth: boolean = false;

  username: string;
  password: string;

  private token: MyJSON = null;


  public isAuthenticated() {
    if (localStorage.getItem("token")) {
      return true;
    }
    return false;
  }

  public getUsername(){
      return localStorage.getItem("username");
  }

  login(username: string, password: string): Promise<string> {
    const body = { username, password };
    const loginUrl = '/api/authenticate';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    var redirectUrl = null;

    return this.http.post<any>(loginUrl, body, {
      headers,
    }).toPromise()
      .then(result => {
        this.token = result;
        localStorage.setItem("username", username);
        localStorage.setItem("token", this.token.jwt);
      })
      .then(result => {
        return this.getRoles(username);
      }
      )
      .then(roles => {
        return redirectUrl = this.determineRedirectByRole(roles);
      }
      )
      .then(redirectUrl => {
        return redirectUrl != null ? redirectUrl : "/login";
      }
      );

  }

  loginShelter(username: string, password: string, shelterCode: string): Promise<string> {
    const body = { username, password, shelterCode };
    const loginUrl = '/api/authenticateShelter';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    var redirectUrl = null;

    return this.http.post<any>(loginUrl, body, {
      headers,
    }).toPromise()
      .then(result => {
        this.token = result;
        localStorage.setItem("username", username);
        localStorage.setItem("token", this.token.jwt);
      })
      .then(result => {
        return this.getRoles(username);
      }
      )
      .then(roles => {
        return redirectUrl = this.determineRedirectByRole(roles);
      }
      )
      .then(redirectUrl => {
        return redirectUrl != null ? redirectUrl : "/loginShelter";
      }
      );
  }

  getToken(): string {
    return this.token.jwt;
  }

  getRoles(username: string): Promise<string> {
    let roles: string;
    const rolesUrl = '/api/user/roles';
    const headers = {
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      username: username
    };
    return this.http.get<string>(rolesUrl, { headers, responseType: 'text' as 'json' }).toPromise().then(data => {
      roles = data;
      localStorage.setItem("role", data);
      return roles;
    });
  }


  determineRedirectByRole(role: string): string {
    switch (role) {
      case "Admin": return "/admin";
      case "User": return "/all";
      case "Shelter": return "/all";
    }
  }

}

interface MyJSON {
  jwt: string;
}
