import { Component, OnInit } from '@angular/core';
import { AdService } from '../services/ad/ad.service';
import { PetAdDto } from '../model/petAdDto';
import { Gender } from '../model/gender';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { AlertService } from '../alert/alert.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-ads',
  templateUrl: './user-ads.component.html',
  styleUrls: ['./user-ads.component.scss']
})
export class UserAdsComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  gender: Gender;
  petAds: PetAdDto[] = [];

  constructor(private adService: AdService, private alert: AlertService,
    private confirmationDialogService: ConfirmationDialogService, private router: Router) {
  }

  ngOnInit() {
   this.getAllNewAds()
  }

  confirmDeliting(id: number) {
    this.confirmationDialogService.confirm('Please confirm..', 'Do you want to delete the ad?')
      .then((isConfirmed) => {
        if (isConfirmed) {
          this.deleteAd(id)
        }
      })
  }

  deleteAd(id: number) {
    this.adService.deletePetAd(id).subscribe(() => {
      this.getAllNewAds()
      this.alert.success("<strong>Success!</strong> Your pet ad was deleted!", this.options)
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }

  getAllNewAds() {
    this.adService.getAllPetAdsOfCurrentUser().subscribe(
      (ads) => this.petAds = ads,
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }
}
