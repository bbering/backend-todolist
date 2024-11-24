package com.todolist.backend_todolist.dto;

import org.springframework.beans.BeanUtils;

import com.todolist.backend_todolist.model.Task;
import com.todolist.backend_todolist.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

  private Long id;

  private User user;

  private String taskDescription;

  public TaskDTO(Task task) {
    BeanUtils.copyProperties(task, this);
  }
}
