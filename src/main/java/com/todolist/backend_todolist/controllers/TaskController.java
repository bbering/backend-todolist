package com.todolist.backend_todolist.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.todolist.backend_todolist.dto.TaskDTO;
import com.todolist.backend_todolist.services.TaskService;

@RestController
public class TaskController {

  @Autowired
  private TaskService taskService;

  @GetMapping("/tasks/list")
  public ResponseEntity<?> listAllTasks() {
    List<TaskDTO> tasks = taskService.findAllTasks();
    return new ResponseEntity<>(tasks, HttpStatus.FOUND);
  }

  @PostMapping("/tasks/save")
  public ResponseEntity<?> saveTask(@RequestBody TaskDTO taskDTO) {
    try {
      TaskDTO createdTask = taskService.createTask(taskDTO);
      return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/tasks/list/{id}")
  public ResponseEntity<?> listTask(@PathVariable Long id) {
    TaskDTO foundTask = taskService.findTaskById(id);
    return new ResponseEntity<>(foundTask, HttpStatus.FOUND);
  }

  @PutMapping("/tasks/update/{id}")
  public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
    TaskDTO taskToUpdate = taskService.findTaskById(id);
    taskService.updateTask(taskDTO, id);
    return new ResponseEntity<>(taskToUpdate, HttpStatus.OK);
  }

  @DeleteMapping("/tasks/delete/{id}")
  public ResponseEntity<?> deleteTask(@PathVariable Long id) {
    taskService.deleteTask(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
