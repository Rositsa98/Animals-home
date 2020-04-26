import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../services/registration/registration.service';
import { User } from '../model/user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  private user:User;
  
  private regError:boolean = false;

  private agreePolicies:boolean = false;
  private agreeGDPR:boolean = false;

  constructor(private registrationService:RegistrationService, private route:Router) { }

  ngOnInit() {
  }

  registrationForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phoneNumber: new FormControl(''),
    email: new FormControl(''),
    imageUrls: new FormControl(''),

  });

  sumbitData(){

    this.user = 
    {
    username : this.registrationForm.get("username").value,
    password : this.registrationForm.get("password").value,
    firstName : this.registrationForm.get("firstName").value,
    lastName : this.registrationForm.get("lastName").value,
    phoneNumber : this.registrationForm.get("phoneNumber").value,
    roles : "ROLE_USER",
    email : this.registrationForm.get("email").value,
    imageUrls : this.registrationForm.get("imageUrls").value,
    address : "",
    shelterCode : "",
    description : ""
    }

    var result = this.registrationService.registerUser(this.user)
    .then(result => { if(result===true) {this.route.navigateByUrl("/main"); window.location.reload;} 
                      else {this.regError = true;} window.location.reload; });

    console.log("User registered: " + result);
  
  }

}
