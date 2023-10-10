package com.spring.security.spring.boot.security.service;

import com.spring.security.spring.boot.security.model.Person;
import com.spring.security.spring.boot.security.model.Role;
import com.spring.security.spring.boot.security.repository.PersonRepository;
import com.spring.security.spring.boot.security.repository.RoleDAO;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final RoleDAO roleDAO;



    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, RoleDAO roleDAO) {
        this.personRepository = personRepository;
        this.roleDAO = roleDAO;


    }


    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(long id) {
        Person user = null;
        Optional<Person> optional = personRepository.findById(id);
        if(optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public Person save(Person user) {
        personRepository.save(user);
        return user;
    }

    @Override
    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    @Override
    public Person findByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
   //@PostConstruct
    public void addDefaultUser() {
        List<Role> roles1 = new ArrayList<>();
        roles1.add(roleDAO.findById(1L).orElse(null));
        List<Role> roles2 = new ArrayList<>();
        roles2.add(roleDAO.findById(1L).orElse(null));
        roles2.add(roleDAO.findById(2L).orElse(null));
        Person user1 = new Person("Steve","Jobs",(byte) 25, "user@mail.com","12345",roles1);
        Person user2 = new Person("Garry","Potter",(byte) 30, "admin@mail.com","admin",roles2);
        save(user1);
        save(user2);
    }


    @Override
    public Person update(Person user) {
        if(user == null){
            throw new NullPointerException("User is empty");
        }
        personRepository.save(user);
        return user;
    }

//    @Override
//    public Person passwordCoder(Person user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return user;
//    }
}
