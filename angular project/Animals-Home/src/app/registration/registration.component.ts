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

  constructor(private registrationService:RegistrationService, private route:Router) { }

  ngOnInit() {
  }


  registrationForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phoneNumber: new FormControl(''),
    roles: new FormControl(''),
    email: new FormControl(''),
    imageUrls: new FormControl('')
  });

  sumbitData(){

    var result = this.registrationService.registerUser(this.registrationForm.value)
    .then(result => { if(result===true) {this.route.navigateByUrl("/main"); window.location.reload;} 
                      else {this.regError = true;} window.location.reload; });

    console.log("User registered: " + result);
  
  }

}
