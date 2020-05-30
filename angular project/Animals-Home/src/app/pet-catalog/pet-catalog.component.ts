import { Component, OnInit } from '@angular/core';
import { PetAdDto } from '../model/petAdDto';
import { AdService } from '../services/ad/ad.service';
import { Gender } from '../model/gender';
import { AlertService } from '../alert/alert.service';
import { Router } from '@angular/router';
import { NavbarService } from '../navigation/navbar.service';

@Component({
  selector: 'app-pet-catalog',
  templateUrl: './pet-catalog.component.html',
  styleUrls: ['./pet-catalog.component.scss']
})
export class PetCatalogComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  gender: Gender;
  petAds: PetAdDto[] = [];

  constructor(private adService: AdService, private alert: AlertService, private navigation: NavbarService, private router: Router) {
  }

  ngOnInit() {
    this.navigation.show();
    this.getAds();
  }

  getAds() {
    let newAds: PetAdDto[] = [];
    this.adService.getAllPetAds().subscribe((ads) => {
      newAds = ads;
      this.petAds = newAds;
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }

  addPetAdToFavorites(id: number) {
    this.adService.addPetAdToFavorites(id).subscribe(() => {
      this.alert.success("<strong>Success!</strong> That was added to Favorites.", this.options)
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }
}
