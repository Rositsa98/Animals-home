import { PetDetailsDto } from './petDetailsDto';
import { PetHabitsDto } from './petHabitsDto';

export class PetDto {
    petDetailsDto: PetDetailsDto;
    petHabitsDto: PetHabitsDto;
    petDescription: string;

    public setPetDetails(petDetailsDto: PetDetailsDto){
        this.petDetailsDto = petDetailsDto;
    }

    public setPetHabits(petHabitsDto: PetHabitsDto){
        this.petHabitsDto = petHabitsDto;
    }

    public setPetDescription(petDescription: string){
        this.petDescription = petDescription;
    }
}