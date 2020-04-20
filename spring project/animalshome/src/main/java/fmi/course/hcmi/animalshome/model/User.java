package fmi.course.hcmi.animalshome.model;

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
@Table(name="users", schema="animalsHome")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {
    @Id
    @Column(name="id", unique = true, nullable = false)
    private Long id;

    @NonNull
    @NotNull
    @Size(min = 3, max = 60)
    @Column(name="username", nullable = false)
    private String username;

    @NonNull
    @NotBlank
    @Size(min=3, max=12)
    @Column(name="password", nullable = false)
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="roles")
    private String roles;

    private boolean active = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] roles = this.roles.split(",");
        List<String> rolesList = Collections.arrayToList(roles);

        return rolesList
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public String getRoles() {
        return roles;
    }


    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}