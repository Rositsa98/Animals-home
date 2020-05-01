package fmi.course.hcmi.animalshome.model;

import fmi.course.hcmi.animalshome.entity.PetAd;
import io.jsonwebtoken.lang.Collections;
import lombok.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user", schema = "animalshome")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class User implements UserDetails {

    public User() {

    }

    public User(final Long id,
                String username,
                String password,
                final String phoneNumber,
                final String roles,
                final String email,
                final String imageUrls,
                final String address,
                boolean active,
                List<PetAd> favouritePets) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.email = email;
        this.imageUrls = imageUrls;
        this.address = address;
        this.active = active;
        this.favouritePets = favouritePets;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @NotNull
    @Size(min = 3, max = 60)
    @Column(name = "username", nullable = false)
    protected String username;

    @NonNull
    @NotBlank
    @Size(min = 3, max = 12)
    @Column(name = "password", nullable = false)
    protected String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "roles")
    private String roles;

    @Column(name = "email")
    private String email;

    @Column(name = "image_urls")
    private String imageUrls;

    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "favourites_users", joinColumns = {@JoinColumn(name = "users_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "favourites_id", referencedColumnName = "id")})
    private List<PetAd> favouritePets;

    protected boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roles = getRoles().split(",");
        List<String> rolesList = Collections.arrayToList(roles);

        return rolesList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<PetAd> getFavouritePets() {
        return favouritePets;
    }

    public void setFavouritePets(List<PetAd> favouritePets) {
        this.favouritePets = favouritePets;
    }
}