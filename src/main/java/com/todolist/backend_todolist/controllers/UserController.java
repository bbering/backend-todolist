package com.todolist.backend_todolist.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.backend_todolist.dto.UserDTO;
import com.todolist.backend_todolist.model.User;
import com.todolist.backend_todolist.services.UserService;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/list")
  public ResponseEntity<?> listUsers() {
    List<UserDTO> users = userService.getAllUsers();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {
    try {
      UserDTO createdUser = userService.createUser(userDTO);
      return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/list-user/{id}")
  public ResponseEntity<?> findUser(@PathVariable Long id) {
    UserDTO foundUser = userService.getUserById(id);
    return new ResponseEntity<>(foundUser, HttpStatus.FOUND);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
    return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.ACCEPTED);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}