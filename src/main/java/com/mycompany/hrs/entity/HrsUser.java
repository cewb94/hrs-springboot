package com.mycompany.hrs.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "HRS_USERS", schema = "HRS")
public class HrsUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="USER_ID")
    private Long id;

    @Column(name="USER_NAME", unique=true, nullable=false)
    private String userName;

    @Column(name="PASSWORD", nullable=false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
      name="HRS_USER_ROLES", schema="HRS",
      joinColumns=@JoinColumn(name="USER_ID")
    )
    @Enumerated(EnumType.STRING)
    @Column(name="ROLE")
    private Set<Role> roles;

    // constructors, getters, setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    
}
