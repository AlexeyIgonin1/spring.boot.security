package com.spring.security.spring.boot.security.repository;

import com.spring.security.spring.boot.security.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("select u from Person u join fetch u.roles where u.username = :username")
    Person findByUsername(@Param("username")String username);
}
