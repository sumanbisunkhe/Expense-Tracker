package com.expensetracker.dto;

import com.expensetracker.enums.Gender;
import com.expensetracker.enums.Role;
import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

public class UserDto {

    private Long id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Full Name is mandatory")
    private String fullName;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age must be less than or equal to 100")
    private int age;

    @NotNull(message = "Gender is mandatory")
    private Gender gender;

    @NotEmpty(message = "Roles cannot be empty")
    private Set<Role> roles = new HashSet<>();

    // Constructors, getters, and setters

    public UserDto() {
    }

    public UserDto(Long id, String username, String password, String email, String fullName, String address, int age, Gender gender, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.address = address;
        this.age = age;
        this.gender = gender;
        this.roles = roles;
    }

    public UserDto(String username, String password, String email, String fullName, String address, int age, Gender gender, Set<Role> roles) {
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
