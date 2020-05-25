import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { PetAdDto } from 'src/app/model/petAdDto';

@Component({
  selector: 'app-pet-list',
  templateUrl: './pet-list.component.html',
  styleUrls: ['./pet-list.component.scss']
})
export class PetListComponent implements OnInit {

  @Input() petAds: PetAdDto[];
  @Input() isHiddenFavoriteButton: boolean;
  @Input() isHidenButtonsControls: boolean;
  @Input() isHidenContent: boolean;
  @Output() deletedAd = new EventEmitter();
  @Output() favoritePetAd = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  deleteCurrentAd(id: number) {
    this.deletedAd.emit(id);
  }

  addToFavorites(id: number) {
    this.favoritePetAd.emit(id);
  }
}
