package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class SingleUserDto extends UserDto {
    @NotBlank
    @Size(min = 3, max = 50, message = "The first name must be between 3 and 50 characters long!")
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 50, message = "The last name must be between 3 and 50 characters long!")
    private String lastName;

    @NotBlank @Pattern(regexp = "[0-9]{2}/[0-9]{2}/[0-9]{4}", message = "Invalid date format. The format must be dd/mm/yyyy.")
    //TODO find other regex
    private String birthday;

    @NotNull
    private Gender gender;

    public SingleUserDto() {
    }

    public SingleUserDto(final String username,
                         final String password,
                         final String phoneNumber,
                         final String roles,
                         final String email,
                         final String imageUrls,
                         final String address,
                         final List<PetAd> favouritePets,
                         final String firstName,
                         final String lastName,
                         final String birthday,
                         final Gender gender) {
        super(username, password, phoneNumber, roles, email, imageUrls, address, favouritePets);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }
}
