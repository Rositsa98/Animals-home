import { Component, OnInit, Input } from '@angular/core';
import { PetAdDto } from '../model/petAdDto';
import { PetHabitsDto } from '../model/petHabitsDto';
import { PetDetailsDto } from '../model/petDetailsDto';
import { PhotoDto } from '../model/photoDto';
import { ActivatedRoute, Router } from '@angular/router';
import { PetDto } from '../model/petDto';
import { AlertService } from '../alert/alert.service';
import { AuthenticationService } from '../services/authentication/authentication.service';
import { ConfirmationDialogService } from '../dialog-content/confirmation-dialog.service';
import { AdService } from '../services/ad/ad.service';
import { PetAdWithUser } from '../model/petAdWithUser';
import { UserInfo } from '../model/userInfo';
import { WorkDayDto } from '../model/workDayDto';
import { resolve } from 'url';

@Component({
  selector: 'app-ad-viewer',
  templateUrl: './ad-viewer.component.html',
  styleUrls: ['./ad-viewer.component.scss']
})
export class AdViewerComponent implements OnInit {
  options = {
    autoClose: true,
    keepAfterRouteChange: true
  };

  DEFAULT_IMAGE: string = '../assets/images/default-img.jpg';

  BREED_ICONS: Array<string> = ['../assets/images/dog.png', '../assets/images/cat.png',
    '../assets/images/rabbit.png', '../assets/images/bird.png', '../assets/images/other.png'];

  petAdWithUser: PetAdWithUser = new PetAdWithUser();
  owner: UserInfo = new UserInfo();
  workDayDto: WorkDayDto = new WorkDayDto();
  petAd: PetAdDto = new PetAdDto();
  pet: PetDto = new PetDto();
  petDetails: PetDetailsDto = new PetDetailsDto();
  petHabits: PetHabitsDto = new PetHabitsDto();
  photosName = new Array<PhotoDto>();
  mainPhoto: PhotoDto = new PhotoDto();
  breedIcon: string = null;
  isAdFromUser: boolean;
  isLoggedInUser: boolean = false;

  constructor(private activatedRoute: ActivatedRoute, private alert: AlertService, private authService: AuthenticationService,
    private confirmationDialogService: ConfirmationDialogService, private router: Router, private adService: AdService) {
  }

  async ngOnInit() {
    if (this.authService.isAuthenticated()) {
      this.isLoggedInUser = true;
    } else {
      this.isLoggedInUser = false;
    }
    await this.getAd().then((a: PetAdWithUser) => this.petAdWithUser = a);
    this.displayExistingPetInfo();
  }

  getAd() {
    let currentId = this.getIdOfAd();
    return new Promise(resolve=>{
      this.adService.getPetAdByIdWithUserInfo(currentId).subscribe((ad) => {
        resolve(ad)
      },
        (error) => {
          this.router.navigate(['error/internal-server-error']);
        });
    })
  }

  displayExistingPetInfo() {
    this.petAd = this.petAdWithUser.petAdDto;
    this.owner = this.petAdWithUser.owner;

    if (this.petAdWithUser.owner.roles === 'ROLE_USER') {
      this.isAdFromUser = true;
    } else {
      this.workDayDto = this.owner.workDayDto;
      this.isAdFromUser = false;
    }

    this.pet = this.petAd.petDto;
    this.petDetails = this.pet.petDetailsDto;
    this.petHabits = this.pet.petHabitsDto;

    this.photosName = this.petAd.photosDto;
    if (this.photosName.length > 0) {
      this.mainPhoto = this.photosName[0];
    } else {
      this.mainPhoto = new PhotoDto(this.DEFAULT_IMAGE);
    }
    this.changeBreedIcon();
  }

  changeMainPhoto(selectedPhoto) {
    const index = this.photosName.indexOf(selectedPhoto);
    this.mainPhoto = this.photosName[index];
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

  addToFavorites() {
    if (this.isLoggedInUser) {
      let currentId = this.getIdOfAd();
      this.addPetAdToFavorites(currentId);
    } else {
      this.showLoggedInMessage('You must sign in to see your list with favorite pets.');
    }
  }

  requestToVisitShelter() {
    if (this.isLoggedInUser) {
      this.router.navigate(['/send-request'])
    } else {
      this.showLoggedInMessage('You must sign in to see your list with favorite pets.');
    }
  }

  getIdOfAd() {
    return +this.activatedRoute.snapshot.paramMap.get('id');
  }

  showLoggedInMessage(message: string) {
    this.confirmationDialogService.confirm('Warrning!', message, 'Log in', 'Sign up')
      .then((isConfirmed) => {
        if (isConfirmed) {
          this.router.navigate(['/login'])
        } else {
          this.router.navigate(['/registration'])
        }
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
