package com.atabey.todo_management.repository;

import com.atabey.todo_management.dto.TodoDto;
import com.atabey.todo_management.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<TodoDto> findByIdAndCompletedTrue(Long id);
}
