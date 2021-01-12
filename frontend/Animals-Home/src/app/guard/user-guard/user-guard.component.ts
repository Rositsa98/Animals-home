import { Component, OnInit, Injectable } from '@angular/core';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { Router } from '@angular/router';

@Injectable({providedIn: 'root'})
export class UserGuardComponent {

  constructor(public auth: AuthenticationService, public router: Router) { }

  canActivate(): boolean {
    console.log(localStorage.getItem("token"));

    if (localStorage.getItem("token")==null || localStorage.getItem("token")=="") {
      this.router.navigate(['login']);
      alert("Wrong username or password!")
      return false;
    }
    return true;
  }
}
