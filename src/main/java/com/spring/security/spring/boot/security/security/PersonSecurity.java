package com.spring.security.spring.boot.security.security;

import com.spring.security.spring.boot.security.model.Person;
import com.spring.security.spring.boot.security.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
//implements UserDetails
public class PersonSecurity  {

//    private final Person person;
//    @Autowired
//    public PersonSecurity(Person person) {
//        this.person = person;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//      //  return Collections.singletonList(new SimpleGrantedAuthority(person.getRole()));
//
//        Set<Role> roles = person.getRoles();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        for (Role role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return this.person.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return this.person.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//
//    public Person getPerson(){
//        return this.person;
//    }


}
