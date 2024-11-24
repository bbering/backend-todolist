package com.todolist.backend_todolist.dto;

import org.springframework.beans.BeanUtils;

import com.todolist.backend_todolist.model.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

  private Long id;

  private UserDTO userDTO;

  private String taskDescription;

  public TaskDTO(Task task) {
    BeanUtils.copyProperties(task, this);
    if (task.getUser() != null) {
      this.userDTO = new UserDTO(task.getUser());
    }
  }

}
