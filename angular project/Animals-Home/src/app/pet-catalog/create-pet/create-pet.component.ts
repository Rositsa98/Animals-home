import { Component, OnInit } from '@angular/core';
import { AdService } from 'src/app/services/ad/ad.service';
import { AdArguments } from 'src/app/model/adArguments';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/alert';
import { ConfirmationDialogService } from '../../dialog-content/confirmation-dialog.service';

@Component({
  selector: 'app-create-pet',
  templateUrl: './create-pet.component.html',
  styleUrls: ['./create-pet.component.scss']
})
export class CreatePetComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  constructor(private adService: AdService, private router: Router, private alert: AlertService) { }

  ngOnInit() { }

  savePetAd(newAd: AdArguments) {
    this.adService.createPetAd(newAd.newPetAd, newAd.newPetPhotos).subscribe(() => {
      this.alert.success("<strong>Success!</strong> Your pet ad was created!", this.options)
      this.router.navigate(['/all']);
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }
}
