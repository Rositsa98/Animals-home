import { Component, OnInit } from '@angular/core';
import { AdService } from 'src/app/services/ad/ad.service';
import { AdArguments } from 'src/app/model/adArguments';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/alert';
import { ConfirmationDialogService } from '../../dialog-content/confirmation-dialog.service';
import { ApiService } from 'src/app/services/api.service';
import { PetAdDto } from 'src/app/model/petAdDto';
import { PetType } from 'src/app/model/petType';

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

  constructor(private adService: AdService, 
    private router: Router, 
    private alert: AlertService, 
    private apiService: ApiService) { }

  ngOnInit() { }

  savePetAd(newAd: AdArguments) {
    var photos: Array<File> = [];

    if (newAd.newPetPhotos.length == 0 && newAd.newPetAd.petDto.petDetailsDto.petType == PetType.DOG) {
      let breed = newAd.newPetAd.petDto.petDetailsDto.breed;

      this.apiService.getRandomPhotoUrl(breed).subscribe(response => {
        if (response.status == "success") {
            let imageUrl = response.message as string;
            this.apiService.getPhoto(imageUrl).subscribe(blob => {
              if (blob != null) {
                let fileName = breed + '.' + blob.type.split('/')[1];
                photos = [this.blobToFile(blob, fileName)];
              }

              this.createPetAd(newAd.newPetAd, photos);
            });
        }
      },
      (error) => {
        photos = newAd.newPetPhotos;
        this.createPetAd(newAd.newPetAd, photos);
       });
    }
    else {
      photos = newAd.newPetPhotos;
      this.createPetAd(newAd.newPetAd, photos);
    }
  }

  createPetAd(petAd: PetAdDto, photos: Array<File>) {
    this.adService.createPetAd(petAd, photos).subscribe(() => {
      this.alert.success("<strong>Success!</strong> Your pet ad was created!", this.options)
      this.router.navigate(['/all']);
    },
      (error) => {
        this.router.navigate(['error/internal-server-error']);
      });
  }

  blobToFile = (blob: Blob, fileName:string): File => {
    var file: any = blob;
    
    file.lastModifiedDate = new Date();
    file.name = fileName;

    return <File>file;
  }
}
