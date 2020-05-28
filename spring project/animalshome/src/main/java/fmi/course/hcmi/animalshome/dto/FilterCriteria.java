package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.enums.Gender;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class FilterCriteria {
    private PetType petType;
    private Gender gender;
    private String breed;
    private String city;
    private int age;
    private boolean isFromShelter;
}
