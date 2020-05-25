import { Component, OnInit } from '@angular/core';
import { AdService } from '../services/ad/ad.service';
import { PetAdDto } from '../model/petAdDto';
import { AlertService } from '../alert/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-favorite-ads',
  templateUrl: './user-favorite-ads.component.html',
  styleUrls: ['./user-favorite-ads.component.scss']
})
export class UserFavoriteAdsComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  petAds: PetAdDto[] = [];

  constructor(private adService: AdService, private alert: AlertService, private router: Router) { }

  ngOnInit() {
    this.getFavoritePetAds();
  }

  getFavoritePetAds() {
    this.adService.getCurrentUserFavoritePetAds().subscribe((ads) => {
      this.petAds = ads
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }

  removeAdFromFavorites(id: number) {
    this.adService.removePetAdFromFavorites(id).subscribe(() => {
      this.getFavoritePetAds()
      this.alert.success("<strong>Success!</strong> The ad was removed from Favorites.", this.options)
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }
}
