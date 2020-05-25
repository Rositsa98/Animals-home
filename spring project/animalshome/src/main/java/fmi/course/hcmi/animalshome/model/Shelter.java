package fmi.course.hcmi.animalshome.model;

import fmi.course.hcmi.animalshome.entity.PetAd;
import fmi.course.hcmi.animalshome.entity.WorkDay;
import io.jsonwebtoken.lang.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue(value = "shelter")
public class Shelter extends User implements UserDetails {
    @Column(name = "shelter_code")
    private String shelterCode;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "work_day_id", referencedColumnName = "id")
    private WorkDay workDay;

    //    @Transient
    //    private List<String> visitRequests;//TODO

    public Shelter() {

    }

    public Shelter(final Long id,
                   final String username,
                   final String password,
                   final String phoneNumber,
                   final String roles,
                   final String email,
                   final String imageUrl,
                   final String address,
                   final boolean active,
                   final List<PetAd> favouritePets,
                   final String shelterCode,
                   final String description,
                   final WorkDay workDay) {
        super(id, username, password, phoneNumber, roles, email, imageUrl, address, active, favouritePets);
        this.shelterCode = shelterCode;
        this.description = description;
        this.workDay = workDay;
    }

    public String getShelterCode() {
        return shelterCode;
    }

    public void setShelterCode(String shelterCode) {
        this.shelterCode = shelterCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public void setWorkDay(final WorkDay workDay) {
        this.workDay = workDay;
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
