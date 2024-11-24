package com.todolist.backend_todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.backend_todolist.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}