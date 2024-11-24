package com.todolist.backend_todolist.dto;

import org.springframework.beans.BeanUtils;

import com.todolist.backend_todolist.model.User;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

  private Long id;

  private String username;

  private String password;

  public UserDTO(User user) {
    BeanUtils.copyProperties(user, this);
  }

}
