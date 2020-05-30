import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../services/registration/registration.service';
import { User } from '../model/user';
import { NavbarService } from '../navigation/navbar.service';
import { Gender } from '../model/gender';
import { AlertService } from '../alert/alert.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  user: User;
  genderTypes: string[] = Object.keys(Gender);

  regError: boolean = false;

  agreePolicies: boolean = false;
  agreeGDPR: boolean = false;

  constructor(private registrationService: RegistrationService, private alert: AlertService, private navigation: NavbarService, private route: Router) { }

  ngOnInit() {
    this.navigation.hide();
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

  sumbitData() {

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
      .then(result => {
        if (result === true) {
          this.route.navigateByUrl("/all");
          window.location.reload;
          this.alert.success("<strong>Success!</strong> You are now registered.", this.options)
        }
        else { this.regError = true; } window.location.reload;
      });
  }

}
