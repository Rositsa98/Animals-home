import { PetAdDto } from './petAdDto';
import { PhotoDto } from './photoDto';

export class AdArguments {
    newPetAd: PetAdDto;
    deletedPhotos: Array<PhotoDto>;
    newPetPhotos: Array<File>;

    public constructor(newPetAd: PetAdDto, newPetPhotos: Array<File>) {
        this.setNewPetAd(newPetAd);
        this.setNewPetPhotos(newPetPhotos);
    }

    public setNewPetAd(newPetAd: PetAdDto) {
        this.newPetAd = newPetAd;
    }

    public setDeletedPhotos(deletedPhotos: Array<PhotoDto>) {
        this.deletedPhotos = deletedPhotos;
    }

    public setNewPetPhotos(newPetPhotos: Array<File>) {
        this.newPetPhotos = newPetPhotos;
    }
}