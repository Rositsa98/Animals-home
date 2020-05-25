import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { PetAdDto } from 'src/app/model/petAdDto';
import { PhotoDto } from 'src/app/model/photoDto';
import { PetAdWithUser } from 'src/app/model/petAdWithUser';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  private baseUrl = '/api/pet/ad';

  constructor(private http: HttpClient) { }

  getRequests(): Promise<Request> {
    return null;
  }

  getAllAds() {
    return this.http.get<PetAdDto[]>(`${this.baseUrl}/all`);
  }

  getAdById(id: number) {
    return this.http.get<PetAdDto>(`${this.baseUrl}/${id}`);
  }

  getAdWithUserInfo(id: number) {
    return this.http.get<PetAdWithUser>(`${this.baseUrl}/view/${id}`);
  }

  createAd(newAd: FormData) {
    return this.http.post(`${this.baseUrl}`, newAd, { headers: this.setUserHeaders() });
  }

  editAd(id: number, newAd: FormData) {
    return this.http.patch<PetAdDto>(`${this.baseUrl}/${id}`, newAd, { headers: this.setUserHeaders() }); //TODO
  }

  deleteAd(id: number) {
    return this.http.delete<PetAdDto>(`${this.baseUrl}/${id}`, { headers: this.setUserHeaders() });
  }

  getAllPetAdsOfUser() {
    return this.http.get<PetAdDto[]>(`${this.baseUrl}/all/user`, { headers: this.setUserHeaders() });
  }

  getFavoritePetAds() {
    return this.http.get<PetAdDto[]>(`${this.baseUrl}/all/user/favorite`, { headers: this.setUserHeaders() });
  }

  addAdToFavorites(id: number) {
    let body = new HttpParams()
    .set('id', id.toString());

    console.log(body);
    return this.http.patch<PetAdDto>(`${this.baseUrl}/add/user/favorite`, body, { headers: this.setUserHeaders() });
  }

  removeAdFromFavorites(id: number) {
    let body = new HttpParams()
    .set('id', id.toString());

    return this.http.patch<PetAdDto>(`${this.baseUrl}/remove/user/favorite/`, body, { headers: this.setUserHeaders() });
  }

  setUserHeaders() {
    let headers = new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`
    });

    return headers;
  }

}
