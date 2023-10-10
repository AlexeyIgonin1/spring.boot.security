package com.spring.security.spring.boot.security.service;

import com.spring.security.spring.boot.security.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
//    public List<Person> getAllRoles();
//    public List<Person> getAllPersons();
//    public void savePersonDetail(Person person);
//    public Person getPersonDetail(int id);
//    public void deletePerson(int id);
//    public void updatePerson(Person person);


    List<Person> findAll ();
    Person getById(long id);
    Person save(Person user);
    void deleteById(long id);
    Person findByUsername(String username);
    void addDefaultUser();
    Person update(Person user);
  //  Person passwordCoder(Person user);

}
