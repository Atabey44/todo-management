package com.atabey.todo_management.dto;

import com.atabey.todo_management.model.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoDtoConvert {


    public TodoDto convert(Todo todo){
        TodoDto todoDto = new TodoDto();
        todoDto.setTitle(todo.getTitle());
        todoDto.setDescription(todo.getDescription());
        todoDto.setCompleted(todo.isCompleted());

        return todoDto;
    }
}
