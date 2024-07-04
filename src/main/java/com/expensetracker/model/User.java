package com.expensetracker.model;

import com.expensetracker.enums.Gender;
import com.expensetracker.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    // Additional user details
    private String fullName;

    private String address;

    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    // Constructors, getters, and setters

    public User() {
    }

    public User(String username, String password, String email, String fullName, String address, int age, Gender gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.gender = gender;
    }

    public User(String username, String password, String email, String fullName, String address, int age, Gender gender, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.roles = roles;
    }
    // Getters and setters

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
