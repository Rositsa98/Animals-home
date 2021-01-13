package fmi.course.hcmi.animalshome.dto;

public class PetHabitsDto {
    private boolean isGoodWithDogs;
    private boolean isGoodWithCats;
    private boolean isGoodWithKids;
    private boolean isHouseTrained;
    private boolean isNeutered;

    public PetHabitsDto() {
    }

    public PetHabitsDto(final boolean isGoodWithDogs,
                        final boolean isGoodWithCats,
                        final boolean isGoodWithKids,
                        final boolean isHouseTrained,
                        final boolean isNeutered) {
        this.isGoodWithDogs = isGoodWithDogs;
        this.isGoodWithCats = isGoodWithCats;
        this.isGoodWithKids = isGoodWithKids;
        this.isHouseTrained = isHouseTrained;
        this.isNeutered = isNeutered;
    }

    public boolean getIsGoodWithDogs() {
        return isGoodWithDogs;
    }

    public void setIsGoodWithDogs(final boolean isGoodWithDogs) {
        this.isGoodWithDogs = isGoodWithDogs;
    }

    public boolean getIsGoodWithCats() {
        return isGoodWithCats;
    }

    public void setIsGoodWithCats(final boolean isGoodWithCats) {
        this.isGoodWithCats = isGoodWithCats;
    }

    public boolean getIsGoodWithKids() {
        return isGoodWithKids;
    }

    public void setIsGoodWithKids(final boolean isGoodWithKids) {
        this.isGoodWithKids = isGoodWithKids;
    }

    public boolean getIsHouseTrained() {
        return isHouseTrained;
    }

    public void setIsHouseTrained(final boolean isHouseTrained) {
        this.isHouseTrained = isHouseTrained;
    }

    public boolean getIsNeutered() {
        return isNeutered;
    }

    public void setIsNeutered(final boolean isNeutered) {
        this.isNeutered = isNeutered;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final PetHabitsDto that = (PetHabitsDto) o;

        if (isGoodWithDogs != that.isGoodWithDogs) {
            return false;
        }
        if (isGoodWithCats != that.isGoodWithCats) {
            return false;
        }
        if (isGoodWithKids != that.isGoodWithKids) {
            return false;
        }
        if (isHouseTrained != that.isHouseTrained) {
            return false;
        }
        return isNeutered == that.isNeutered;
    }

    @Override
    public int hashCode() {
        int result = (isGoodWithDogs ? 1 : 0);
        result = 31 * result + (isGoodWithCats ? 1 : 0);
        result = 31 * result + (isGoodWithKids ? 1 : 0);
        result = 31 * result + (isHouseTrained ? 1 : 0);
        result = 31 * result + (isNeutered ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PetHabitsDto{");
        sb.append("isGoodWithDogs=")
                .append(isGoodWithDogs);
        sb.append(", isGoodWithCats=")
                .append(isGoodWithCats);
        sb.append(", isGoodWithKids=")
                .append(isGoodWithKids);
        sb.append(", isHouseTrained=")
                .append(isHouseTrained);
        sb.append(", isNeutered=")
                .append(isNeutered);
        sb.append('}');
        return sb.toString();
    }
}
