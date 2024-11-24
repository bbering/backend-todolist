package com.todolist.backend_todolist.services;

import java.util.Optional;

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

  public UserDTO getUserById(Long id) {
    User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    return mapToDTO(user);
  }

  @Transactional
  public UserDTO createUser(UserDTO userDTO) {
    User user = new User(userDTO);
    userRepository.save(user);
    taskRepository.saveAll(user.getTaskList());
    return mapToDTO(user);
  }

  @Transactional
  public UserDTO updateUser(UserDTO userDTO, Long id) {
    Optional<User> optionalUserToUpdate = userRepository.findById(id);

    if (!optionalUserToUpdate.isPresent()) {
      throw new RuntimeException("User not found with id: " + id);
    }

    else {
      User userToUpdate = optionalUserToUpdate.get();
      userToUpdate.setPassword(userDTO.getPassword());

      userRepository.save(userToUpdate);
      return mapToDTO(userToUpdate);
    }
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
