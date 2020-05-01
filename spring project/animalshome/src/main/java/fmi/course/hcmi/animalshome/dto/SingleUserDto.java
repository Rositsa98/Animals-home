package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.enums.Gender;
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
public class SingleUserDto extends UserDto {
    @NonNull
    @NotNull
    private String firstName;

    @NonNull
    @NotNull
    private String lastName;

    @NonNull
    @NotNull
    private String birthday;

    private Gender gender;

    public SingleUserDto(final String username,
                         final String password,
                         final String phoneNumber,
                         final String roles,
                         final String email,
                         final String imageUrls,
                         final String address,
                         final List<PetAd> favouritePets,
                         @NonNull @NotNull final String firstName,
                         @NonNull @NotNull final String lastName,
                         @NonNull @NotNull final String birthday,
                         final Gender gender) {
        super(username, password, phoneNumber, roles, email, imageUrls, address, favouritePets);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }
}
