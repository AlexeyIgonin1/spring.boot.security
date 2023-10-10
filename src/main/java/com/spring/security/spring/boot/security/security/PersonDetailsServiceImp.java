package com.spring.security.spring.boot.security.security;

import com.spring.security.spring.boot.security.model.Person;
import com.spring.security.spring.boot.security.repository.PersonRepository;
import com.spring.security.spring.boot.security.security.PersonSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
@Service
public class PersonDetailsServiceImp implements UserDetailsService {

private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsServiceImp(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Person person = personRepository.findByUsername(s);
//
//        if(person.isEmpty())
//            throw new UsernameNotFoundException("User not Found!");
//
//        return new PersonSecurity.getPerson();

        try {
            return personRepository.findByUsername(s);
        } catch (UsernameNotFoundException u) {
            throw new UsernameNotFoundException("user not found");
        }

    }
}
