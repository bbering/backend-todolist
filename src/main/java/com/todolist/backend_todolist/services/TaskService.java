package com.todolist.backend_todolist.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.backend_todolist.dto.TaskDTO;
import com.todolist.backend_todolist.dto.UserDTO;
import com.todolist.backend_todolist.model.Task;
import com.todolist.backend_todolist.model.User;
import com.todolist.backend_todolist.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private UserService userService;

  public TaskDTO mapToDTO(Task task) {
    TaskDTO taskDTO = new TaskDTO(task);
    return taskDTO;
  }

  public TaskDTO findTaskById(Long id) {
    Optional<Task> foundTask = taskRepository.findById(id);
    return mapToDTO(foundTask.orElseThrow(() -> new RuntimeException(
        "Usuario não encontrado")));
  }

  @Transactional
  public TaskDTO createTask(TaskDTO taskDTO) {
    UserDTO userDTO = userService.getUserById(taskDTO.getUser().getId());
    User user = new User(userDTO);
    Task task = new Task(taskDTO);
    task.setId(null);
    task.setUser(user);
    taskRepository.save(task);
    return mapToDTO(task);
  }

  @Transactional
  public TaskDTO updateTask(TaskDTO taskDTO) {
    Task task = new Task(taskDTO);
    task.setTaskDescription(taskDTO.getTaskDescription());
    return mapToDTO(taskRepository.save(task));
  }

  public void deleteTask(Long id) {
    try {
      taskRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Não foi possivel encontrar uma task com esse ID");
    }
  }

}
