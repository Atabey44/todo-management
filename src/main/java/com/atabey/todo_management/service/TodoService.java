package com.atabey.todo_management.service;

import com.atabey.todo_management.dto.CreateTodoDto;
import com.atabey.todo_management.dto.TodoDto;
import com.atabey.todo_management.dto.TodoDtoConvert;
import com.atabey.todo_management.dto.UpdateTodoDto;
import com.atabey.todo_management.exception.ResourceNotFoundException;
import com.atabey.todo_management.model.Todo;
import com.atabey.todo_management.model.User;
import com.atabey.todo_management.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoDtoConvert todoDtoConvert;


    public List<TodoDto> getAllTodo() {
        return todoRepository.findAll()
                .stream()
                .map(todoDtoConvert::convert)
                .collect(Collectors.toList());
    }

    public TodoDto getByIdTodo(Long id) {
       Todo todo = todoRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo not  found with id :  "+id));

       return todoDtoConvert.convert(todo);
    }

    public TodoDto addTodo(CreateTodoDto createTodoDto) {
        Todo todo = new Todo();
        todo.setTitle(createTodoDto.getTitle());
        todo.setDescription(createTodoDto.getDescription());
        todo.setCompleted(createTodoDto.isCompleted());
        return todoDtoConvert.convert(todoRepository.save(todo));
    }


    public TodoDto updateTodo(Long id, UpdateTodoDto updateTodoDto) {
        return null;
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo not  found with id :  "+id));
    }

    public TodoDto completedTodo(Long id){

        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo not  found with id :  "+ id));

        todo.setCompleted(Boolean.TRUE);
        Todo updateTodo = todoRepository.save(todo);
        return todoDtoConvert.convert(updateTodo);
    }

    public TodoDto inCompletedTodo(Long id){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()->
                        new ResourceNotFoundException("Todo not  found with id :  "+ id));


        todo.setCompleted(Boolean.FALSE);
        Todo todoDto = todoRepository.save(todo);
        return todoDtoConvert.convert(todoDto);
    }


}
