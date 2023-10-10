package com.spring.security.spring.boot.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class Person  implements UserDetails {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[A-Za-zа-яёА-ЯЁ]{2,15}", message = "Name should be between 2 and 15 characters without space")
    @Column(name="username")
    private String username;

    @Pattern(regexp = "[A-Za-zа-яёА-ЯЁ]{2,15}", message = "Surname should be between 2 and 15 characters without space")
    @Column(name="surname")
    private String surname;


    @Min(value = 0, message = "Age should be >= 0 & < 128")
    @Max(value = 127, message = "Age should be >= 0 & < 128")
    @Column(name="age")
    private int age;

    @Pattern(regexp = "([A-z0-9_.-]+)@([A-z0-9_.-]+).([A-z]{2,8})", message = "Enter correct email")
    @Column(name="email")
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, message = "Password should be greater then 4 symbols")
    @Column(name="password")
    private String password;

//    @Column
//    private String test;

    @NotEmpty(message = "The role cannot be omitted")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))

    private List<Role> roles;

    public Person() {
    }

    public Person(String username, String surname, int age, String email, String password, List<Role> roles) {
        this.username = username;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

//    public Person(Long id, String username, String surname, int age, String email, String password, Set<Role> roles) {
//        this.id = id;
//        this.username = username;
//        this.surname = surname;
//        this.age = age;
//        this.email = email;
//        this.password = password;
//        this.roles = roles;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getUsername() {
//        return username;
//    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getPassword() {
//        return password;
//    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));

        List<Role> roles = getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
       // return this.person.getPassword();

        return password;
    }

    @Override
    public String getUsername() {
        //return this.person.getUsername();
        return username;
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


//    public Person getPerson(){
//        return this.person;
//    }

}
