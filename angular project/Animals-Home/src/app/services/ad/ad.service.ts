import { Injectable } from '@angular/core';
import { RequestService } from '../request/request.service';
import { Observable } from 'rxjs';
import { PetAdDto } from 'src/app/model/petAdDto';
import { PhotoDto } from 'src/app/model/photoDto';
import { PetAdWithUser } from 'src/app/model/petAdWithUser';

@Injectable({
  providedIn: 'root'
})
export class AdService {

  constructor(private request: RequestService) { }

  getAllPetAds(): Observable<PetAdDto[]> {
    return this.request.getAllAds();
  }

  createPetAd(petAd: PetAdDto, photos: Array<File>): Observable<any> {
    const data = this.formDataBuilder(petAd, photos);

    return this.request.createAd(data);
  }

  editPetAd(petAd: PetAdDto, newPetAdPhotos: Array<File>, deletedPetAdPhotos: Array<PhotoDto>): Observable<PetAdDto> {
    const id = petAd.id;
    const data = this.formDataBuilder(petAd, newPetAdPhotos);

    
    var dataBuilder = new FormData();

    dataBuilder.append('petAdDto', new Blob([JSON.stringify(petAd)], { type: "application/json" }));
    dataBuilder.append('deletedPhotos', new Blob([JSON.stringify(deletedPetAdPhotos)], { type: "application/json" }));
    for (let index = 0; index < newPetAdPhotos.length; index++) {
      const element = newPetAdPhotos[index];
      dataBuilder.append('files', element, element.name);
    }


    return this.request.editAd(id, dataBuilder);
  }

  getPetAdById(id: number): Observable<PetAdDto> {
    return this.request.getAdById(id);
  }

  getPetAdByIdWithUserInfo(id: number): Observable<PetAdWithUser> {
    return this.request.getAdWithUserInfo(id);
  }

  deletePetAd(id: number): Observable<PetAdDto> {
    return this.request.deleteAd(id);
  }

  getAllPetAdsOfCurrentUser(): Observable<PetAdDto[]> {
    return this.request.getAllPetAdsOfUser();
  }

  getCurrentUserFavoritePetAds(): Observable<PetAdDto[]> {
    return this.request.getFavoritePetAds();
  }

  removePetAdFromFavorites(id: number): Observable<PetAdDto> {
    return this.request.removeAdFromFavorites(id);
  }

  addPetAdToFavorites(id: number): Observable<PetAdDto> {
    return this.request.addAdToFavorites(id);
  }

  formDataBuilder(petAd: PetAdDto, photos: Array<File>) {
    var dataBuilder = new FormData();

    dataBuilder.append('petAdDto', new Blob([JSON.stringify(petAd)], { type: "application/json" }));
    for (let index = 0; index < photos.length; index++) {
      const element = photos[index];
      dataBuilder.append('files', element, element.name);
    }

    return dataBuilder;
  }
}
