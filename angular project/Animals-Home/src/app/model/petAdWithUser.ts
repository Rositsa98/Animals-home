import { PetAdDto } from './petAdDto';
import { UserInfo } from './userInfo';

export class PetAdWithUser {
    petAdDto: PetAdDto;
    owner: UserInfo;

    public setpetAdDto(petAdDto: PetAdDto) {
        this.petAdDto = petAdDto;
    }

    public setOwner(owner: UserInfo) {
        this.owner = owner;
    }
}