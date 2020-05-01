package fmi.course.hcmi.animalshome.model;

import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.enums.Gender;
import io.jsonwebtoken.lang.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue(value = "single_user")
public class SingleUser extends User implements UserDetails {

    public SingleUser() {

    }

    public SingleUser(final Long id,
                      final String username,
                      final String password,
                      final String firstName,
                      final String lastName,
                      final String phoneNumber,
                      final String roles,
                      final String email,
                      final String imageUrl,
                      final String address,
                      final boolean active,
                      final List<PetAd> favouritePets,
                      final String birthday,
                      final Gender gender) {
        super(id, username, password, phoneNumber, roles, email, imageUrl, address, active, favouritePets);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
    }

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthday")
    private String birthday;

    @Column(name = "gender")
    private Gender gender;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roles = super.getRoles()
                .split(",");
        List<String> rolesList = Collections.arrayToList(roles);

        return rolesList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return super.password;
    }

    @Override
    public String getUsername() {
        return super.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.active;
    }

    @Override
    public boolean isEnabled() {
        return super.active;
    }
}
