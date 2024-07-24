package com.demo.ToDo_SpringBootApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.ToDo_SpringBootApp.dto.Todo;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Integer>{
	
}
