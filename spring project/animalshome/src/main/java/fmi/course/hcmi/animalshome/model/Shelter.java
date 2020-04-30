package fmi.course.hcmi.animalshome.model;

import fmi.course.hcmi.animalshome.enums.Gender;
import fmi.course.hcmi.animalshome.service.impl.UserService;
import io.jsonwebtoken.lang.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@DiscriminatorValue(value = "shelter")
public class Shelter extends User implements UserDetails {

    public Shelter(){

    }

    public Shelter(Long id, String username, String password,
                   String phoneNumber, String roles, String email, String imageUrl, String address,
                   boolean active, String shelterCode, String description){
        super(id, username, password, phoneNumber, roles, email, imageUrl, address,
                active);
        this.shelterCode = shelterCode;
        this.description = description;
    }

    @Column(name="shelter_code")
    private String shelterCode;

    @Column(name="description")
    private String description;

    //TODO
    @Transient
    private String workDay;

    @Transient
    private List<String> favouritePets;

    @Transient
    private List<String> ads;

    @Transient
    private List<String> visitRequests;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roles = super.getRoles().split(",");
        List<String> rolesList = Collections.arrayToList(roles);

        return rolesList
                .stream()
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
