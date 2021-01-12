package fmi.course.hcmi.animalshome.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PetType {
    DOG, CAT, RABBIT, BIRD, OTHER;

    private String petType;
}
