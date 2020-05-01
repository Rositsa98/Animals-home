package fmi.course.hcmi.animalshome.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class PetDto {
    @NotNull
    @Valid
    private PetDetailsDto petDetailsDto;

    @NotNull
    @Valid
    private PetHabitsDto petHabitsDto;

    @NotBlank
    @Size(min = 10, max = 500, message = "The description of the pet must be between 10 and 500 characters long!")
    private String petDescription;
}
