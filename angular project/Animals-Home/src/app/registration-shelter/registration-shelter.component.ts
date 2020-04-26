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


  private user: User;

  private regError: boolean = false;

  latitude: number;
  longitude: number;
  zoom: number;

  constructor(private registrationService: RegistrationService, private route: Router) { }

  ngOnInit() {
    this.setCurrentLocation();
  }

  // Get Current Location Coordinates
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 15;
      });
    }
  }


  registrationFormShelter = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    phoneNumber: new FormControl(''),
    email: new FormControl(''),
    imageUrls: new FormControl(''),
    address: new FormControl(''),
    shelterCode: new FormControl(''),
    description: new FormControl('')

  });

  sumbitData() {

    this.user =
    {
      username: this.registrationFormShelter.get("username").value,
      password: this.registrationFormShelter.get("password").value,
      firstName: "",
      lastName: "",
      phoneNumber: this.registrationFormShelter.get("phoneNumber").value,
      roles: "ROLE_SHELTER",
      email: this.registrationFormShelter.get("email").value,
      imageUrls: this.registrationFormShelter.get("imageUrls").value,
      address: this.registrationFormShelter.get("address").value,
      shelterCode: this.registrationFormShelter.get("shelterCode").value,
      description: this.registrationFormShelter.get("description").value
    }

    var result = this.registrationService.registerUser(this.user)
      .then(result => {
        if (result === true) { this.route.navigateByUrl("/main"); window.location.reload; }
        else { this.regError = true; } window.location.reload;
      });

    console.log("User registered: " + result);

  }

}
