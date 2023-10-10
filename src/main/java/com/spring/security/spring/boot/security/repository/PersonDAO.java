package com.spring.security.spring.boot.security.repository;

import com.spring.security.spring.boot.security.model.Person;

import java.util.List;

public interface PersonDAO {
    public List<Person> getAllRoles();
    public List<Person> getAllPersons();
    public void savePersonDetail(Person person);
    public Person getPersonDetail(int id);
    public void deletePerson(int id);
    public void updatePerson(Person person);
}
