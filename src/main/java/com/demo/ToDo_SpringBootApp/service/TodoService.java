package com.demo.ToDo_SpringBootApp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.ToDo_SpringBootApp.dao.TodoDao;
import com.demo.ToDo_SpringBootApp.dto.Todo;

@Service
public class TodoService {

	@Autowired
	private TodoDao todoDao;

	public boolean saveTodo(Todo todo) {
		Todo saveTodo = todoDao.saveTodo(todo);
		if (getTodo(saveTodo.getId()) != null)
			return true;
		return false;
	}

	public List<Todo> getAllTodos() {
		ArrayList<Todo> todoList = new ArrayList<Todo>();
		todoDao.getAllTodos().forEach(todo -> todoList.add(todo));
		return todoList;
	}

	public Todo getTodo(int id) {
		return todoDao.getTodo(id);
	}

	public boolean deleteTodo(int id) {
		int deletedId = todoDao.deleteTodo(id);
		if (todoDao.getTodo(deletedId) == null)
			return true;
		return false;
	}

	public boolean updateStatus(int id) {
		Todo todo = todoDao.getTodo(id);
		todo.setStatus("Completed");
		Todo savedTodo = todoDao.saveTodo(todo);
		if (savedTodo.getStatus().equals("Completed"))
			return true;
		return false;
	}

	public boolean updateTodo(Todo todo) {
		Todo updatedTodo = todoDao.updateTodo(todo);
		if (getTodo(updatedTodo.getId()) != null)
			return true;
		return false;
	}
}