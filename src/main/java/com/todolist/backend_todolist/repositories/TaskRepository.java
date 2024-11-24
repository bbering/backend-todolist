package com.todolist.backend_todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todolist.backend_todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}