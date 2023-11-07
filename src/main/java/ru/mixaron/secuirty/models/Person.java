package ru.mixaron.secuirty.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mixaron.secuirty.util.Role;

import java.util.*;

@Entity
@Table(name = "shop_person")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email")
    @Email(message = "wrong form email address")
    @NotEmpty(message = "email must be not empty")
    private String email;

    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "name")
    @NotEmpty
    private String username;

    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "person")
    Set<Order> orders;

    public Person(String email) {
        this.email = email;
    }

    public Person(String mail, Role role) {
        this.email = mail;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
