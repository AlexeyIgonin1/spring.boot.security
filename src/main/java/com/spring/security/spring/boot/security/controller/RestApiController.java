package com.spring.security.spring.boot.security.controller;


import com.spring.security.spring.boot.security.Exception.ExceptionInfo;
import com.spring.security.spring.boot.security.Exception.UserUsernameExistException;
import com.spring.security.spring.boot.security.model.Person;
import com.spring.security.spring.boot.security.service.PersonServiceImpl;
import com.spring.security.spring.boot.security.service.RoleServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestApiController {

  private final PersonServiceImpl personServiceImp;
    private final RoleServiceImpl roleServiceImpl;


    @Autowired
    public RestApiController(PersonServiceImpl personServiceImp, RoleServiceImpl roleServiceImpl) {
        this.personServiceImp = personServiceImp;
        this.roleServiceImpl = roleServiceImpl;
    }


    @GetMapping("/users")
    public ResponseEntity<List<Person>> getUsers() {
        return new ResponseEntity<>(personServiceImp.findAll(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<ExceptionInfo> createUser(@Valid @RequestBody Person user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = getErrorsFromBindingResult(bindingResult);
            return new ResponseEntity<>(new ExceptionInfo(error), HttpStatus.BAD_REQUEST);
        }
        try {
            personServiceImp.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UserUsernameExistException u) {
            throw new UserUsernameExistException("User with username exist");
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<ExceptionInfo> pageDelete(@PathVariable("id") long id) {
        personServiceImp.deleteById(id);
        return new ResponseEntity<>(new ExceptionInfo("User deleted"), HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<Person> getUser (@PathVariable("id") long id) {
        Person user = personServiceImp.getById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<Person> getUserByUsername (Principal principal) {
        Person user = personServiceImp.findByUsername(principal.getName());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<ExceptionInfo> pageEdit(@PathVariable("id") long id,
                                                  @Valid @RequestBody Person user,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String error = getErrorsFromBindingResult(bindingResult);
            return new ResponseEntity<>(new ExceptionInfo(error), HttpStatus.BAD_REQUEST);
        }
        try {
            String oldPassword = personServiceImp.getById(id).getPassword();
            if (oldPassword.equals(user.getPassword())) {
                System.out.println("TRUE");
                user.setPassword(oldPassword);
                personServiceImp.update(user);
            } else {
                System.out.println("FALSE");
                personServiceImp.save(user);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (UserUsernameExistException u) {
            throw new UserUsernameExistException("User with username exist");
        }
    }

    private String getErrorsFromBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
    }



}
