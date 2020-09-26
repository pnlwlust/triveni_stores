package com.proha.TriveniStores.domain;

import lombok.*;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean locked = false;
    private boolean enabled = false;
    @Transient
    private String confirmPassword;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime = new Date();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        final SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(Roles.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }
*/
}
