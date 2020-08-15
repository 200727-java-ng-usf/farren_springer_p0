package com.revature.revabank.models;

import java.util.Objects;

public class AppUser {

    // fields/attributes
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Role role;

    // constructors
    public AppUser() {
        super();
    }

    public AppUser(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = Role.LOCKED;
    }

    public AppUser(String firstName, String lastName, String username, String password, Role role) {
        this(firstName, lastName, username, password);
        this.role = role;
    }

    public AppUser(Integer id, String firstName, String lastName, String username, String password, Role role) {
        this(firstName, lastName, username, password, role);
        this.id = id;
    }

    // copy constructor (used for conveniently copying the values of one AppUser to create a new instance with those values)
    public AppUser(AppUser copy) {
        this(copy.id, copy.firstName, copy.lastName, copy.username, copy.password, copy.role);
    }

    // getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // overridden Object methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) &&
                Objects.equals(firstName, appUser.firstName) &&
                Objects.equals(lastName, appUser.lastName) &&
                Objects.equals(username, appUser.username) &&
                Objects.equals(password, appUser.password) &&
                role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, username, password, role);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

}
