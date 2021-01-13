package fmi.course.hcmi.animalshome.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Gender {
    MALE, FEMALE;

    private String gender;
}
