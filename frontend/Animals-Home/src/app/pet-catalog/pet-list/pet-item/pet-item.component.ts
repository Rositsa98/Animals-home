import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PetAdDto } from 'src/app/model/petAdDto';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication/authentication.service';
import { ConfirmationDialogService } from 'src/app/dialog-content/confirmation-dialog.service';

@Component({
  selector: 'app-pet-item',
  templateUrl: './pet-item.component.html',
  styleUrls: ['./pet-item.component.scss']
})
export class PetItemComponent implements OnInit {
  DEFAULT_IMAGE: string = '../assets/images/default-img.jpg';

  @Input() petAd: PetAdDto;
  @Input() isHiddenFavoriteButton: boolean;
  @Input() isHidenButtonsControls: boolean;
  @Input() isHidenContent: boolean;
  @Output() deletedPetAd = new EventEmitter();
  @Output() favoritePetAd = new EventEmitter();

  constructor(private authService: AuthenticationService, private confirmationDialogService: ConfirmationDialogService, private router: Router) { }

  ngOnInit() {
  }

  editAd() {
    const id = this.petAd.id;
    this.router.navigate(['edit/', id]);
  }

  deleteAd() {
    const currentId = this.petAd.id;
    this.deletedPetAd.emit(currentId);
  }

  addToFavorites() {
    if (this.authService.isAuthenticated()) {
      const currentId = this.petAd.id;
      this.favoritePetAd.emit(currentId);
    } else {
      this.confirmationDialogService.confirm('Warrning!', 'You must sign in to add in favorites.', 'Log in', 'Sign up')
      .then((isConfirmed) => {
        if (isConfirmed) {
          this.router.navigate(['/login'])
        } else {
          this.router.navigate(['/registration'])
        }
      });
    }

  }

  showPetAd() {
    this.router.navigate(['view-ad/', this.petAd.id]);
  }

  getImagePath() {
    if(this.petAd.photosDto && this.petAd.photosDto.length > 0) {
      return this.petAd.photosDto[0].photoName;
    }

    return this.DEFAULT_IMAGE;
  }
}
