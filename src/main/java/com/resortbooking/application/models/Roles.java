package com.resortbooking.application.models;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="role")
    private String role;

    // Mapped by 'role' field in User class
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> users;

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    // hashCode, equals, toString
    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Roles other)) return false;
        return Objects.equals(id, other.id) && Objects.equals(role, other.role);
    }

    @Override
    public String toString() {
        return "Roles [id=" + id + ", role=" + role + "]";
    }
}
