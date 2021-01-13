export class PetHabitsDto {
    isGoodWithDogs: boolean;
    isGoodWithCats: boolean;
    isGoodWithKids: boolean;
    isHouseTrained: boolean;
    isNeutered: boolean;

    public setIsGoodWithDogs(isGoodWithDogs: boolean){
        this.isGoodWithDogs = isGoodWithDogs
    }

    public setIsGoodWithCats(isGoodWithCats: boolean){
        this.isGoodWithCats = isGoodWithCats
    }

    public setIsGoodWithKids(isGoodWithKids: boolean){
        this.isGoodWithKids = isGoodWithKids
    }

    public setIsHouseTrained(isHouseTrained: boolean){
        this.isHouseTrained = isHouseTrained
    }

    public setIsNeutered(isNeutered: boolean){
        this.isNeutered = isNeutered
    }
}