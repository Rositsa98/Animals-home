import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges, OnChanges } from '@angular/core';
import { PetAdDto } from 'src/app/model/petAdDto';
import { Gender } from 'src/app/model/gender';
import { PetType } from 'src/app/model/petType';
import { PetDto } from 'src/app/model/petDto';
import { PetDetailsDto } from 'src/app/model/petDetailsDto';
import { PetHabitsDto } from 'src/app/model/petHabitsDto';
import { AdArguments } from 'src/app/model/adArguments';
import { PhotoDto } from 'src/app/model/photoDto';
import { NavbarService } from 'src/app/navigation/navbar.service';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-pet-form',
  templateUrl: './pet-form.component.html',
  styleUrls: ['./pet-form.component.scss']
})
export class PetFormComponent implements OnChanges {
  DEFAULT_IMAGE: string = '../assets/images/default-img.jpg';
  DEFAULT_COLOR: string = '#000000';
  BREED_ICONS: Array<string> = ['../assets/images/dog.png', '../assets/images/cat.png',
    '../assets/images/rabbit.png', '../assets/images/bird.png', '../assets/images/other.png'];

  @Input() existingPetAd: PetAdDto;
  @Input() isCreateAd: boolean;
  @Output() newPetAd = new EventEmitter<AdArguments>();

  months = Array.from(Array(12).keys());
  ages = Array.from([...Array(20).keys()].map(index => index + 1));
  genderTypes: string[] = Object.keys(Gender);
  petTypes: string[] = Object.keys(PetType);
  photosName = new Array<PhotoDto>();
  newPhotosFile = new Array<File>();
  deletedPhotosName = new Array<PhotoDto>();
  mainPhoto: PhotoDto = null;
  isDefaultCloseButtonShown: boolean;
  breedIcon: string = null;
  petAd: PetAdDto;
  pet: PetDto;
  petDetails: PetDetailsDto;
  petHabits: PetHabitsDto;
  dogBreeds = new Array<string>();

  constructor(private navigation: NavbarService, private apiService: ApiService) {
    //TODO add validation
    //TODO show breed in select tag
    //TODO show city in select tag
    this.fillDogBreeds();
  }

  ngOnChanges() {
    this.navigation.show();
    if (typeof this.existingPetAd !== "undefined" && this.existingPetAd !== null) {
      this.displayExistingPetInfo();
    } else {
      this.displayDefaultPetInfo();
    }
  }

  fillDogBreeds() {
    this.apiService.getDogBreeds().subscribe(response => {
      if (response.status == "success") {
        for (const property in response.message) {
          if (response.message[property].length > 0) {
            response.message[property].forEach(element => {
              this.dogBreeds.push(`${property} ${element}`);
            });
          }
          else {
            this.dogBreeds.push(property);
          }
        }
      } 
    });
  }

  onFileChanged(event) {
    let files = event.target.files;
    if (files) {
      for (let file of files) {
        let reader = new FileReader();
        reader.onload = (e: any) => {
          let result = e.target.result;
          let photo: PhotoDto = new PhotoDto(result);

          if (this.newPhotosFile.length === 1) {
            this.mainPhoto = photo;
            this.isDefaultCloseButtonShown = true;
          }

          this.photosName.push(photo);
        }
        reader.readAsDataURL(file);
        this.newPhotosFile.push(file);
      }
    }
  }

  removeMainPhoto() {
    const index = this.photosName.indexOf(this.mainPhoto);
    if (index >= 0) {
      if (this.photosName.length - 1 > 0) {
        this.mainPhoto = this.photosName[index + 1];
      } else {
        this.mainPhoto = new PhotoDto(this.DEFAULT_IMAGE);
        this.isDefaultCloseButtonShown = false;
      }
      this.deletedPhotosName.push(this.photosName[index]);
      this.removePhoto(index);
    }
  }

  removeSelectedPhoto(selectedPhoto) {
    const index = this.photosName.indexOf(selectedPhoto);
    if (!this.isCreateAd && index > 0) {
      this.deletedPhotosName.push(this.photosName[index]);
    }
    this.removePhoto(index);
  }

  removePhoto(index: number) {
    if (this.photosName.length > 0) {
      this.photosName.splice(index, 1);
    }
    if (this.newPhotosFile.length > 0) {
      this.newPhotosFile.splice(index, 1);
    }
  }

  displayExistingPetInfo() {
    this.petAd = this.existingPetAd;
    this.pet = this.petAd.petDto;
    this.petDetails = this.petAd.petDto.petDetailsDto;
    this.petHabits = this.petAd.petDto.petHabitsDto;

    this.photosName = this.petAd.photosDto;
    this.mainPhoto = this.photosName[0];
    this.changeBreedIcon();

    for (let index = 0; index < this.photosName.length; index++) {
      this.newPhotosFile.push(new File([], ""));
    }
    this.isDefaultCloseButtonShown = true;
  }

  displayDefaultPetInfo() {
    this.petAd = new PetAdDto();
    this.pet = new PetDto();
    this.petDetails = new PetDetailsDto();
    this.petHabits = new PetHabitsDto();

    this.petDetails.petType = PetType["DOG"];
    this.petDetails.color = this.DEFAULT_COLOR;
    this.petDetails.age = this.ages[0];
    this.petDetails.months = this.months[0];
    this.petDetails.gender = Gender["MALE"];
    this.breedIcon = this.BREED_ICONS[0];

    this.mainPhoto = new PhotoDto(this.DEFAULT_IMAGE);
    this.isDefaultCloseButtonShown = false;
  }

  changeBreedIcon() {
    switch (this.petDetails.petType) {
      case "DOG":
        this.breedIcon = this.BREED_ICONS[0];
        break;
      case "CAT":
        this.breedIcon = this.BREED_ICONS[1];
        break;
      case "RABBIT":
        this.breedIcon = this.BREED_ICONS[2];
        break;
      case "BIRD":
        this.breedIcon = this.BREED_ICONS[3];
        break;
      case "OTHER":
        this.breedIcon = this.BREED_ICONS[4];
        break;
    }
  }

  createAd() {
    let newAd = new PetAdDto();
    newAd = this.petAd;
    newAd.petDto = this.pet;
    newAd.petDto.petDetailsDto = this.petDetails;
    newAd.petDto.petHabitsDto = this.petHabits;

    let adArguments = new AdArguments(newAd, this.newPhotosFile);

    this.newPetAd.emit(adArguments);
  }

  saveAd() {
    let newAd = new PetAdDto();
    newAd = this.petAd;
    newAd.petDto = this.pet;
    newAd.petDto.petDetailsDto = this.petDetails;
    newAd.petDto.petHabitsDto = this.petHabits;

    let newFiles: Array<File> = new Array<File>();
    for (const file of this.newPhotosFile) {
      if (file.name) {
        newFiles.push(file);
      }
    }
    let adArguments = new AdArguments(newAd, newFiles);
    adArguments.setDeletedPhotos(this.deletedPhotosName);

    this.newPetAd.emit(adArguments);
  }
}
