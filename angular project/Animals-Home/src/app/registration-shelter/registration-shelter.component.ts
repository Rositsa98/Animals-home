import { Component, OnInit, ViewChild, ElementRef, NgZone } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { RegistrationService } from '../services/registration/registration.service';
import { Router } from '@angular/router';
import { Shelter } from '../model/shelter';
import { AgmMap, MapsAPILoader } from '@agm/core';

@Component({
  selector: 'app-registration-shelter',
  templateUrl: './registration-shelter.component.html',
  styleUrls: ['./registration-shelter.component.scss']
})
export class RegistrationShelterComponent implements OnInit {

  shelter: Shelter;

  public regError: boolean = false;

  public agreePolicies:boolean=false;

  public agreeGDPR:boolean=false;


  public latitude: number;
  public longitude: number;
  public zoom: number;
  public address: string;

  public geoCoder;

  

  
  constructor(private registrationService: RegistrationService, private route: Router,
    private mapsAPILoader: MapsAPILoader,
    private ngZone: NgZone) { }

  ngOnInit() {
    //load Places Autocomplete
    // this.mapsAPILoader.load().then(() => {
    //   this.setCurrentLocation();
    //   this.geoCoder = new google.maps.Geocoder;
 
    //   // let autocomplete = new google.maps.places.Autocomplete(this.searchElementRef.nativeElement);
    //   // autocomplete.addListener("place_changed", () => {
    //   //   this.ngZone.run(() => {
    //   //     //get the place result
    //   //     let place: google.maps.places.PlaceResult = autocomplete.getPlace();
 
    //   //     //verify result
    //   //     if (place.geometry === undefined || place.geometry === null) {
    //   //       return;
    //   //     }
 
    //   //     //set latitude, longitude and zoom
    //   //     this.latitude = place.geometry.location.lat();
    //   //     this.longitude = place.geometry.location.lng();
    //   //     this.zoom = 12;
    //     });
    //   });
    // });
  }

  // Get Current Location Coordinates
  private setCurrentLocation() {
    if ('geolocation' in navigator) {
      navigator.geolocation.getCurrentPosition((position) => {
        this.latitude = position.coords.latitude;
        this.longitude = position.coords.longitude;
        this.zoom = 8;
        this.getAddress(this.latitude, this.longitude);
      });
    }
  }
 
 
  markerDragEnd($event: any) {
    console.log($event);
    this.latitude = $event.coords.lat;
    this.longitude = $event.coords.lng;
    this.getAddress(this.latitude, this.longitude);
  }
 
  getAddress(latitude, longitude) {
    this.geoCoder.geocode({ 'location': { lat: latitude, lng: longitude } }, (results, status) => {
      console.log(results);
      console.log(status);
      if (status === 'OK') {
        if (results[0]) {
          this.zoom = 12;
          this.address = results[0].formatted_address;
        } else {
          window.alert('No results found');
        }
      } else {
        window.alert('Geocoder failed due to: ' + status);
      }
 
    });
  }


  registrationFormShelter = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    phoneNumber: new FormControl(''),
    email: new FormControl(''),
    imageUrls: new FormControl(''),
    address: new FormControl(''),
    shelterCode: new FormControl(''),
    description: new FormControl('')

  });

  sumbitData() {

    this.shelter =
    {
      username: this.registrationFormShelter.get("username").value,
      password: this.registrationFormShelter.get("password").value,
      phoneNumber: this.registrationFormShelter.get("phoneNumber").value,
      roles: "ROLE_SHELTER",
      email: this.registrationFormShelter.get("email").value,
      imageUrls: this.registrationFormShelter.get("imageUrls").value,
      address: this.registrationFormShelter.get("address").value,
      active:true,
      shelterCode: this.registrationFormShelter.get("shelterCode").value,
      description: this.registrationFormShelter.get("description").value
    }

    var result = this.registrationService.registerShelter(this.shelter)
      .then(result => {
        if (result === true) { this.route.navigateByUrl("/main"); window.location.reload; }
        else { this.regError = true; } window.location.reload;
      });

    console.log("User registered: " + result);

  }

}
