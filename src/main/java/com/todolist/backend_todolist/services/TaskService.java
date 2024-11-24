package com.todolist.backend_todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  public List<TaskDTO> mapToDTO(List<Task> tasks) {
    return tasks.stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public TaskDTO findTaskById(Long id) {
    Optional<Task> foundTask = taskRepository.findById(id);
    return mapToDTO(foundTask.orElseThrow(() -> new RuntimeException(
        "Usuario não encontrado")));
  }

  public List<TaskDTO> findAllTasks() {
    List<Task> tasks = taskRepository.findAll();
    return mapToDTO(tasks);
  }

  @Transactional
  public TaskDTO createTask(TaskDTO taskDTO) {
    if (taskDTO.getUserDTO() == null || taskDTO.getUserDTO().getId() == null) {
      throw new IllegalArgumentException("UserDTO or UserDTO.id cannot be null");
    }

    UserDTO userDTO = userService.getUserById(taskDTO.getUserDTO().getId());
    User user = new User(userDTO);

    Task task = new Task(taskDTO);
    task.setId(null);
    task.setUser(user);

    taskRepository.save(task);

    return mapToDTO(task);
  }

  @Transactional
  public TaskDTO updateTask(TaskDTO taskDTO, Long id) {
    Task taskToUpdate = taskRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Task not found"));

    if (taskDTO.getTaskDescription() != null
        && !taskDTO.getTaskDescription().equals(taskToUpdate.getTaskDescription())) {
      taskToUpdate.setTaskDescription(taskDTO.getTaskDescription());
      taskRepository.save(taskToUpdate);
    }

    return mapToDTO(taskToUpdate);
  }

  public void deleteTask(Long id) {
    try {
      taskRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Não foi possivel encontrar uma task com esse ID");
    }
  }

}
