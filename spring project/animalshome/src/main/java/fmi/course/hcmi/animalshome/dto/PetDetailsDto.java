package fmi.course.hcmi.animalshome.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PetDetailsDto {
    @NotBlank
    @Size(min = 2, max = 50, message = "The name of the pet must be between 2 and 50 characters long!")
    private String petName;

    @NotBlank
    private String breed;

    @NotBlank
    private String color;

    @NotNull
    private String city;

    @Min(value = 0)
    @Max(value = 20)
    private int age;

    @Min(value = 0)
    @Max(value = 11)
    private int months;

    @NotNull
    private Gender gender;

    @Min(value = 1)
    @Max(value = 500)
    private double weight;

    @NotNull
    private PetType petType;
}