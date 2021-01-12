package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.entity.PetAd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ShelterDto extends UserDto {

    @NotBlank
    private String shelterCode;

    @NotBlank
    @Size(min = 10, max = 500, message = "The description must be between 10 and 500 characters long!")
    private String description;

    private WorkDayDto workDayDto;

    public ShelterDto(final String username,
                      final String password,
                      final String phoneNumber,
                      final String roles,
                      final String email,
                      final String imageUrls,
                      final String address,
                      final List<PetAd> favouritePets,
                      final String shelterCode,
                      final String description,
                      final WorkDayDto workDayDto) {
        super(username, password, phoneNumber, roles, email, imageUrls, address, favouritePets);
        this.shelterCode = shelterCode;
        this.description = description;
        this.workDayDto = workDayDto;
    }

    //private List<String> visitRequests;

}
