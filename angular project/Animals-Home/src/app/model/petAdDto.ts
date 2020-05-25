import { PetDto } from './petDto';
import { PhotoDto } from './photoDto';

export class PetAdDto {
    id: number;
    petDto: PetDto;
    photosDto: Array<PhotoDto>;

   public setId(id: number){
        this.id = id;
   }

   public setPet(petDto: PetDto){
    this.petDto = petDto;
   }

    public setPhotos(photosDto: Array<PhotoDto>){
        this.photosDto = photosDto;
    }
}