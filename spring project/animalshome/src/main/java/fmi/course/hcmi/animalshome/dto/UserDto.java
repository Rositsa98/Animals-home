package fmi.course.hcmi.animalshome.dto;

import fmi.course.hcmi.animalshome.entity.PetAd;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public abstract class UserDto {

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

    @NonNull
    @NotNull
    @Size(min = 3, max = 60)
    protected String username;

    @NonNull
    @NotBlank
    @Size(min = 3, max = 12)
    protected String password;

    private String phoneNumber;

    private String roles;

    private String email;

    private String imageUrls;

    private String address;

    private List<PetAd> favouritePets;
}
