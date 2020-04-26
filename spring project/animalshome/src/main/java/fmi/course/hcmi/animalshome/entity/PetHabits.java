package fmi.course.hcmi.animalshome.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "pet_habits", schema = "animalsHome")
@Entity
public class PetHabits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "is_good_with_dogs")
    private boolean isGoodWithDogs;

    @Column(name = "is_good_with_cats")
    private boolean isGoodWithCats;

    @Column(name = "is_good_with_kids")
    private boolean isGoodWithKids;

    @Column(name = "is_house_trained")
    private boolean isHouseTrained;

    @Column(name = "is_neutered")
    private boolean isNeutered;

    @OneToOne(mappedBy = "petHabits")
    private Pet pet;

    public PetHabits() {
    }

    public PetHabits(final long id,
                     final boolean isGoodWithDogs,
                     final boolean isGoodWithCats,
                     final boolean isGoodWithKids,
                     final boolean isHouseTrained,
                     final boolean isNeutered,
                     final Pet pet) {
        this.id = id;
        this.isGoodWithDogs = isGoodWithDogs;
        this.isGoodWithCats = isGoodWithCats;
        this.isGoodWithKids = isGoodWithKids;
        this.isHouseTrained = isHouseTrained;
        this.isNeutered = isNeutered;
        this.pet = pet;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
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

    public Pet getPet() {
        return pet;
    }

    public void setPet(final Pet pet) {
        this.pet = pet;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final PetHabits petHabits = (PetHabits) o;

        if (id != petHabits.id) {
            return false;
        }
        if (isGoodWithDogs != petHabits.isGoodWithDogs) {
            return false;
        }
        if (isGoodWithCats != petHabits.isGoodWithCats) {
            return false;
        }
        if (isGoodWithKids != petHabits.isGoodWithKids) {
            return false;
        }
        if (isHouseTrained != petHabits.isHouseTrained) {
            return false;
        }
        if (isNeutered != petHabits.isNeutered) {
            return false;
        }
        return pet.equals(petHabits.pet);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (isGoodWithDogs ? 1 : 0);
        result = 31 * result + (isGoodWithCats ? 1 : 0);
        result = 31 * result + (isGoodWithKids ? 1 : 0);
        result = 31 * result + (isHouseTrained ? 1 : 0);
        result = 31 * result + (isNeutered ? 1 : 0);
        result = 31 * result + pet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PetHabits{");
        sb.append("id=")
                .append(id);
        sb.append(", isGoodWithDogs=")
                .append(isGoodWithDogs);
        sb.append(", isGoodWithCats=")
                .append(isGoodWithCats);
        sb.append(", isGoodWithKids=")
                .append(isGoodWithKids);
        sb.append(", isHouseTrained=")
                .append(isHouseTrained);
        sb.append(", isNeutered=")
                .append(isNeutered);
        sb.append(", pet=")
                .append(pet);
        sb.append('}');
        return sb.toString();
    }
}
