package com.atabey.todo_management.controller;

import com.atabey.todo_management.dto.CreateTodoDto;
import com.atabey.todo_management.dto.TodoDto;
import com.atabey.todo_management.dto.TodoDtoConvert;
import com.atabey.todo_management.dto.UpdateTodoDto;
import com.atabey.todo_management.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private final TodoDtoConvert todoDtoConvert;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/getAllTodo")
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> todoDtoList = todoService.getAllTodo();
        return ResponseEntity.ok(todoDtoList);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}/getByIdTodo")
    public ResponseEntity<TodoDto> getByIdTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.getByIdTodo(id);
        return ResponseEntity.ok(todoDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/addTodo")
    public ResponseEntity<TodoDto>  addTodo(
            @RequestBody CreateTodoDto createTodoDto){
        TodoDto savedTodo = todoService.addTodo(createTodoDto);
        return new ResponseEntity<>(savedTodo,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}/updateTodo")
    public ResponseEntity<TodoDto> updateTodo(
            @PathVariable Long id,
            @RequestBody UpdateTodoDto updateTodoDto){
        return ResponseEntity.ok(
                todoService.updateTodo(id, updateTodoDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/deleteTodo")
    public ResponseEntity<String> deleteTodo(
            @PathVariable Long id){
       todoService.deleteTodo(id);
       return ResponseEntity.ok("Todo successfully deleted");
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/completed")
    public ResponseEntity<TodoDto> completedTodo(@PathVariable Long id){
        TodoDto updateTodo = todoService.completedTodo(id);

        return ResponseEntity.ok(updateTodo);
        }

   @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PatchMapping("/{id}/inCompletedTodo")
    public ResponseEntity<TodoDto> inCompletedTodo(@PathVariable Long id)
        {
            TodoDto todoDto = todoService.inCompletedTodo(id);
            return ResponseEntity.ok(todoDto);
        }

    }




