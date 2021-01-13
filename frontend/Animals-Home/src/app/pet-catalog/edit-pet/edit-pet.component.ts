import { Component, OnInit, Input } from '@angular/core';
import { PetAdDto } from 'src/app/model/petAdDto';
import { Gender } from 'src/app/model/gender';
import { AdService } from 'src/app/services/ad/ad.service';
import { AdArguments } from 'src/app/model/adArguments';
import { Location } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/alert';
import { ConfirmationDialogService } from 'src/app/dialog-content/confirmation-dialog.service';

@Component({
  selector: 'app-edit-pet',
  templateUrl: './edit-pet.component.html',
  styleUrls: ['./edit-pet.component.scss']
})
export class EditPetComponent implements OnInit {

  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  genderTypes: string[] = Object.keys(Gender);
  petAd: PetAdDto;

  constructor(private adService: AdService, private activatedRoute: ActivatedRoute, private location: Location,
    private router: Router, private alert: AlertService, private confirmationDialogService: ConfirmationDialogService) { }

  async ngOnInit() {
    await this.getPetAd().then((ad: PetAdDto)=> this.petAd = ad);
  }

  getPetAd() {
    let currentId = +this.activatedRoute.snapshot.paramMap.get('id');
    return new Promise(resolve=>{
    this.adService.getPetAdById(currentId).subscribe((ad) => {
      resolve(ad);
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
    })
  }

  savePetAd(newAd: AdArguments) {
    this.confirmationDialogService.confirm('Please confirm..', 'Do you want to save the changes?')
      .then((isConfirmed) => {
        if (isConfirmed) {
          this.editPetAd(newAd)
        }
      })
  }

  editPetAd(newAd: AdArguments) {
    this.adService.editPetAd(newAd.newPetAd, newAd.newPetPhotos, newAd.deletedPhotos).subscribe(() => {
      this.alert.success("<strong>Success!</strong> Your pet ad was uppdated!", this.options)
      this.location.back()
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }

}
