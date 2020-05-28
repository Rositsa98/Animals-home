import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PetAdDto } from 'src/app/model/petAdDto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pet-item',
  templateUrl: './pet-item.component.html',
  styleUrls: ['./pet-item.component.scss']
})
export class PetItemComponent implements OnInit {

  @Input() petAd: PetAdDto;
  @Input() isHiddenFavoriteButton: boolean;
  @Input() isHidenButtonsControls: boolean;
  @Input() isHidenContent: boolean;
  @Output() deletedPetAd = new EventEmitter();
  @Output() favoritePetAd = new EventEmitter();

  constructor(private router: Router) { }

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

  addToFavorites(){
    const currentId = this.petAd.id;
    this.favoritePetAd.emit(currentId);
  }

  showPetAd(){
    this.router.navigate(['view-ad/', this.petAd.id]);
  }
}
