package com.demo.ToDo_SpringBootApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.ToDo_SpringBootApp.dto.Todo;
import com.demo.ToDo_SpringBootApp.repository.TodoRepo;

@Repository
public class TodoDao {
	
	@Autowired
	TodoRepo todoRepo;

	public Todo saveTodo(Todo todo) {
		return todoRepo.save(todo);
	}

	public Todo getTodo(int id) {
		Optional<Todo> optional = todoRepo.findById(id);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

	public List<Todo> getAllTodos() {
		return todoRepo.findAll();
	}

	public int deleteTodo(int id) {
		todoRepo.deleteById(id);
		return id;
	}

	public Todo updateTodo(Todo todo) {
		return todoRepo.save(todo);
	}
}