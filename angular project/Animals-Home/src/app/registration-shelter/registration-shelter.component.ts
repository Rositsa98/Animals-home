import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RegistrationService } from '../services/registration/registration.service';
import { Router } from '@angular/router';
import { User } from '../model/user';

@Component({
  selector: 'app-registration-shelter',
  templateUrl: './registration-shelter.component.html',
  styleUrls: ['./registration-shelter.component.scss']
})
export class RegistrationShelterComponent implements OnInit {


  private user:User;
  
  private regError:boolean = false;

  constructor(private registrationService:RegistrationService, private route:Router) { }

  ngOnInit() {
  }


  registrationFormShelter = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phoneNumber: new FormControl(''),
    roles: new FormControl(''),
    email: new FormControl(''),
    imageUrls: new FormControl(''),
    address: new FormControl(''),
    shelterCode: new FormControl(''),
    description: new FormControl('') 

  });

  sumbitData(){

    var result = this.registrationService.registerUser(this.registrationFormShelter.value)
    .then(result => { if(result===true) {this.route.navigateByUrl("/main"); window.location.reload;} 
                      else {this.regError = true;} window.location.reload; });

    console.log("User registered: " + result);
  
  }

}
