package com.todolist.backend_todolist.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.todolist.backend_todolist.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class User {

  @OneToMany
  private List<Task> taskList = new ArrayList<Task>();

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @Column(name = "password")
  @NotBlank
  @Size(min = 8, max = 64)
  private String password;

  public User(UserDTO userDTO) {
    BeanUtils.copyProperties(userDTO, this);
  }

}
