package com.spring.security.spring.boot.security.repository;


import com.spring.security.spring.boot.security.model.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDAO {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Person> getAllRoles() {

//        "SELECT DISTINCT a.city FROM Address a"
//        "Select UPPER(e.role) from Person e"

       // return entityManager.createQuery("SELECT DISTINCT e.role FROM Person e", Person.class).getResultList();
        return null;
    }


    @Override
    @Transactional
    public List<Person> getAllPersons() {
        return entityManager.createQuery("SELECT e from Person e", Person.class).getResultList();
    }


    @Override
    @Transactional
    public void savePersonDetail(Person person) {
        entityManager.persist(person);
        entityManager.flush();
        System.out.println("--Data Saved--");
    }

    @Override
    @Transactional
    public Person getPersonDetail(int id) {
        Person person = entityManager.find(Person.class, id);
        entityManager.detach(person);
        return person;
    }

    @Override
    @Transactional
    public void deletePerson(int id) {
        Person person = entityManager.find(Person.class, id);//извлечение сущности
        entityManager.remove(person);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void updatePerson(Person person) {
        entityManager.merge(person);
        entityManager.flush();
    }
}
