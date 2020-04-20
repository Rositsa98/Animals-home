import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) {

  }

  isAuth = false;

  username: string;
  password: string;

  private token: MyJSON = null;


  public isAuthenticated(): boolean {

    return this.isAuth;
  }


  login(username: string, password: string): Promise<string> {

    const body = { username, password };
    const loginUrl = '/clientServer/authenticate';
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    var redirectUrl = null;

    return this.http.post<any>(loginUrl, body, {
      headers,
    }).toPromise()
      .then(result => {
        this.token = result;
        console.log("Authentication token: " + this.token.jwt);
        localStorage.setItem("token", this.token.jwt);

      })
      .then(result => {
        return this.getRoles(username);
      }
      )
       .then(roles => {
        console.log("User roles:" + roles);
        console.log("Determining redirect url" );
        return redirectUrl = this.determineRedirectByRole(roles);
      }
      )
      .then(redirectUrl =>{
        return redirectUrl != null ? redirectUrl : "/login";
      }
      );

  }

  getToken(): string {
    return this.token.jwt;
  }

  getRoles(username: string): Promise<string> {
    let roles: string;
    const rolesUrl = '/clientServer/api/user/roles';
    const headers = {
      Authorization: 'Bearer ' + localStorage.getItem('token'),
      username: username
    };
    return this.http.get<string>(rolesUrl, { headers, responseType: 'text' as 'json' }).toPromise().then(data => {
      roles = data;
      console.log("getRoles of user:" + data);
      localStorage.setItem("role", data);
      return roles;
    });
  }


  determineRedirectByRole(role: string): string {
    switch (role) {
      case "Admin": return "/admin";
      case "User": return "/main";
      case "Shelter": return "/index";
    }
  }

}

interface MyJSON {
  jwt: string;
}
