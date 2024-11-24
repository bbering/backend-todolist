package com.todolist.backend_todolist.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todolist.backend_todolist.dto.UserDTO;
import com.todolist.backend_todolist.model.User;
import com.todolist.backend_todolist.repositories.TaskRepository;
import com.todolist.backend_todolist.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private TaskRepository taskRepository;

  public UserDTO mapToDTO(User user) {
    UserDTO userDTO = new UserDTO(user);
    return userDTO;
  }

  public List<UserDTO> mapToDTO(List<User> users) {
    return users.stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  public List<UserDTO> getAllUsers() {
    List<User> users = userRepository.findAll();
    return mapToDTO(users);
  }

  public UserDTO getUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    return mapToDTO(user);
  }

  @Transactional
  public UserDTO createUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setPassword(userDTO.getPassword());
    userRepository.save(user);
    taskRepository.saveAll(user.getTaskList());
    return mapToDTO(user);
  }

  @Transactional
  public UserDTO updateUser(User userDTO, Long id) {
    User userToUpdate = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    userToUpdate.setUsername(userDTO.getUsername());
    userToUpdate.setPassword(userDTO.getPassword());
    userRepository.save(userToUpdate);
    return mapToDTO(userToUpdate);
  }

  public void deleteUser(Long id) {
    Optional<User> userToDelete = userRepository.findById(id);

    if (!userToDelete.isPresent()) {
      throw new RuntimeException("User not found with id: " + id);
    } else {
      userRepository.deleteById(id);
    }
  }
}
