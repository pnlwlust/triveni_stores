package com.proha.TriveniStores.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private User user;

    public UserRole(User user, Role role){
        this.user = user;
        this.role = role;
    }

    public UserRole() {

    }
}
