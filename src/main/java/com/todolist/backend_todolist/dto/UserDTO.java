package com.todolist.backend_todolist.dto;

import org.springframework.beans.BeanUtils;

import com.todolist.backend_todolist.model.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Long id;

  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(min = 8, max = 64)
  private String password;

  public UserDTO(User user) {
    BeanUtils.copyProperties(user, this);
  }

}
