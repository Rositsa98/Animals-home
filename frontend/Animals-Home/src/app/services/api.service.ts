import { Injectable } from '@angular/core';
import { CatFactApiResponse } from 'src/app/model/catFactApiResponse';
import { Observable } from 'rxjs';
import { RequestService } from './request/request.service';
import { DogApiResponse } from '../model/dogApiResponse';

@Injectable({
  providedIn: 'root'
})

export class ApiService {

    constructor(private requestService: RequestService) { }
    
    getCatFact(): Observable<CatFactApiResponse> {
        return this.requestService.getCatFact();
    }

    getRandomPhotoUrl(breed: string): Observable<DogApiResponse> {
        return this.requestService.getRandomPhotoUrl(breed);
    }    

    getPhoto(url: string): Observable<Blob> {
        return this.requestService.getPhoto(url);
    }

    getDogBreeds(): Observable<DogApiResponse> {
        return this.requestService.getDogBreeds();
    }
}
