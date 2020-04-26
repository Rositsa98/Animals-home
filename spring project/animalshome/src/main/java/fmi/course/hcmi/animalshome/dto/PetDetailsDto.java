package fmi.course.hcmi.animalshome.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PetDetailsDto {
    @NotEmpty
    @Size(min = 2, max = 50)
    private String petName;

    @NotNull
    private String breed;

    @NotNull
    private String color;

    @Range(min = 0, max = 20)
    private int age;

    @Range(min = 0, max = 11)
    private int months;

    @NotNull
    private Gender gender;

    @Range(max = 500)//TODO add min validation
    private double weight;

    @NotNull
    private PetType petType;
}