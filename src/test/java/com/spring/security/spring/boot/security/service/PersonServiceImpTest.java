package com.spring.security.spring.boot.security.service;

import com.spring.security.spring.boot.security.model.Person;
import com.spring.security.spring.boot.security.model.Role;
import com.spring.security.spring.boot.security.repository.PersonRepository;
import com.spring.security.spring.boot.security.repository.RoleDAO;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImpTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private RoleDAO roleDAO;

    @InjectMocks
    private PersonServiceImpl personService;

    private Person person;

    private List<Person> personList;


    @BeforeEach
    public void setUp() throws Exception{
        System.out.println("Run before every test");
    }

    @AfterEach
    public void tearDown() throws Exception{
        System.out.println("Run after each test");
    }


//    @BeforeEach
//    public void setUp(){
//        person = Person.builder()
//                .id(2L)
//                .firstName("Tony")
//                .lastName("Stark")
//                .email("tony@gmail.com")
//                .build();
//    }

    private Person getPerson(){
     Person person1 = new Person();
     Role role1 = new Role();

     role1.setId(1L);
     role1.setRole("ROLE_USER");

     List<Role> listRole1 = new ArrayList<>();
     listRole1.add(role1);

     person1.setId(1L);
     person1.setUsername("Steve");
     person1.setSurname("Jobs");
     person1.setAge(23);
     person1.setEmail("user@mail.com");
     person1.setPassword("user");
     person1.setRoles(listRole1);

     return person1;
    }

    public List<Person> getListPerson(){
        Person person1 = new Person();
        Person person2 = new Person();

        Role role1 = new Role();
        Role role2 = new Role();

        role1.setId(1L);
        role1.setRole("ROLE_USER");

        role2.setId(2L);
        role2.setRole("ROLE_ADMIN");

        List<Role> listRole1 = new ArrayList<>();
        listRole1.add(role1);

        List<Role> listRole2 = new ArrayList<>();
        listRole2.add(role2);

        person1.setId(1L);
        person1.setUsername("Steve");
        person1.setSurname("Jobs");
        person1.setAge(23);
        person1.setEmail("user@mail.com");
        person1.setPassword("user");
        person1.setRoles(listRole1);

        person2.setId(2L);
        person2.setUsername("Garry");
        person2.setSurname("Potter");
        person2.setAge(30);
        person2.setEmail("admin@mail.com");
        person2.setPassword("admin");
        person2.setRoles(listRole2);

        List<Person> listPerson = new ArrayList<>();
        listPerson.add(person1);
        listPerson.add(person2);

        return listPerson;
    }

    @DisplayName("JUnit test for getAllPerson method (negative scenario)")
    @Test
    public void givenEmptyPersonList_whenGetAllPerson_thenReturnEmptyPersonsList() {
        personList = getListPerson();
        //given(personRepository.findAll()).willReturn(personList);
        Mockito.when(personRepository.findAll()).thenReturn(personList);
        List<Person> returnList = personService.findAll();
        int size = returnList.size();
        assertThat(returnList).isNotNull();
        assertThat(size).isEqualTo(2);
    }

    @DisplayName("JUnit test for savePerson method")
    @Test
    void save(){
        person = getPerson();
        Mockito.when((personRepository.save(person))).thenReturn(person);
        personService.save(person);
    }

    @Test
    public void givenPersonObject_whenSavePerson_thenReturnPersonObject(){
        person = getPerson();
        given(personRepository.save(person)).willReturn(person);
        // когда — действие или поведение, которое мы собираемся протестировать
        Person savePerson = personService.save(person);
        assertThat(savePerson).isNotNull();
    }

    @DisplayName("JUnit test for getPersonById method")
    @Test
    public void givenPersonId_whenGetPersonById_thenReturnPersonObject(){
        person = getPerson();
        given(personRepository.findById(person.getId())).willReturn(Optional.ofNullable(person));
        Person savedPerson = personService.getById(person.getId());
        assertThat(savedPerson).isNotNull();
    }

    @DisplayName("JUnit test for updatePerson method")
    @Test
    public void givenPersonObject_whenUpdatePerson_thenReturnUpdatedPerson(){
        person = getPerson();
        given(personRepository.save(person)).willReturn(person);

        person.setEmail("userNEW@mail.com");
        person.setAge(45);

        Person updatePerson = personService.update(person);
        assertThat(updatePerson).isNotNull();
        assertThat(updatePerson.getEmail()).isEqualTo("userNEW@mail.com");
        assertThat(updatePerson.getAge()).isEqualTo(45);
    }
    //@Disabled("Тест временно отключен.")
    @DisplayName("JUnit test for deletePerson method")
    @Test
    public  void deleteById(){
        person = getPerson();
        //не должен вернуть ничего
        willDoNothing().given(personRepository).deleteById(person.getId());
        personService.deleteById(person.getId());
        verify(personRepository, times(1)).deleteById(person.getId());
    }




    @Test

    public void testUpdateException() {
        person = null;
        //given(personRepository.save(person)).willReturn(person);

        try{
            personService.update(person);
        }
        catch(NullPointerException ex){
            assertTrue(ex instanceof NullPointerException);
        }
    }






}
