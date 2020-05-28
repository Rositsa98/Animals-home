package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.entity.PetAd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public abstract class UserDto {
    @Size(min = 3, max = 60, message = "The username must be between 3 and 60 characters long!")
    @NotBlank
    protected String username;

    @Size(min = 3, max = 12, message = "The password must be between 3 and 12 characters long!")
    @NotBlank
    protected String password;

    @Pattern(regexp = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}")//TODO find other regex
    private String phoneNumber;

    @NotBlank
    private String roles;

    @NotBlank
    @Email(message = "Must be a valid email address")
    private String email;

    private String imageUrls;

    @Size(max = 100, message = "Enter a maximum of 100 characters for the address!")
    private String address;

    private List<PetAd> favouritePets;

    public UserDto() {

    }

    public UserDto(String username,
                   String password,
                   final String phoneNumber,
                   final String roles,
                   final String email,
                   final String imageUrls,
                   final String address,
                   List<PetAd> favouritePets) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.email = email;
        this.imageUrls = imageUrls;
        this.address = address;
        this.favouritePets = favouritePets;
    }
}
