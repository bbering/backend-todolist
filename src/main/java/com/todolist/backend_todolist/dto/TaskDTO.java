package com.todolist.backend_todolist.dto;

import org.springframework.beans.BeanUtils;

import com.todolist.backend_todolist.model.Task;
import com.todolist.backend_todolist.model.User;

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

  private User user;

  private String taskDescription;

  public TaskDTO(Task task) {
    BeanUtils.copyProperties(task, this);
  }
}
