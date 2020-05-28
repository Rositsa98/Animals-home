import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../services/registration/registration.service';
import { User } from '../model/user';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  user:User;

  public regError:boolean = false;

  public agreePolicies:boolean = false;
  public agreeGDPR:boolean = false;


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

    address: new FormControl(''),
    birthday: new FormControl(''),
    gender: new FormControl('')

  });

  sumbitData(){

    var username = this.registrationForm.get("username").value;
    var password = this.registrationForm.get("password").value;
    var firstName = this.registrationForm.get("firstName").value;
    var lastName = this.registrationForm.get("lastName").value;
    var phoneNumber = this.registrationForm.get("phoneNumber").value;
    var roles = "ROLE_USER";
    var email = this.registrationForm.get("email").value;
    var imageUrls = this.registrationForm.get("imageUrls").value;
    var address = this.registrationForm.get("address").value;
    var birthday = this.registrationForm.get("birthday").value;
    var gender = this.registrationForm.get("gender").value;
    

    var result = this.registrationService.registerUser(username, password, firstName, lastName,
      phoneNumber, roles, email, imageUrls, address, birthday, gender)
    .then(result => { if(result===true) {this.route.navigateByUrl("/main"); window.location.reload;} 
                      else {this.regError = true;} window.location.reload; });

    console.log("User registered: " + result);
  
  }

}
