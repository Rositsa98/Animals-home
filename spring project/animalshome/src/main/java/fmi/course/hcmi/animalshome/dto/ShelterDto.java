package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.entity.WorkDay;
import fmi.course.hcmi.animalshome.model.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ShelterDto extends UserDto {

    @NonNull
    @NotNull
    private String shelterCode;

    @NonNull
    @NotNull
    private String description;

    private WorkDay workDay;

    public ShelterDto(final String username,
                      final String password,
                      final String phoneNumber,
                      final String roles,
                      final String email,
                      final String imageUrls,
                      final String address,
                      final List<PetAd> favouritePets,
                      @NonNull @NotNull final String shelterCode,
                      @NonNull @NotNull final String description,
                      final WorkDay workDay) {
        super(username, password, phoneNumber, roles, email, imageUrls, address, favouritePets);
        this.shelterCode = shelterCode;
        this.description = description;
        this.workDay = workDay;
    }

    //private List<String> visitRequests;

}
