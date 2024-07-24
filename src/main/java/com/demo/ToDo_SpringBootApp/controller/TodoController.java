package com.demo.ToDo_SpringBootApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.ToDo_SpringBootApp.dto.Todo;
import com.demo.ToDo_SpringBootApp.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	TodoService todoService;

	@GetMapping({ "/", "TodoList" })
	public String index(Model model, @ModelAttribute("msg") String msg) {
		model.addAttribute("todoList", todoService.getAllTodos());
		model.addAttribute("msg", msg);
		return "TodoList";
	}

	@GetMapping("/add")
	public String addTodo(Model model) {
		model.addAttribute("newTodo", new Todo());
		return "AddTodo";
	}

	@RequestMapping("/updateStatus/{id}")
	public String updateStatus(@PathVariable Integer id, RedirectAttributes redAtt) {
		if (todoService.updateStatus(id)) {
			redAtt.addFlashAttribute("msg", "Edit Success");
//			redAtt.addFlashAttribute("todoList", todoService.getAllTodos());
			return "redirect:/TodoList";
		}
		redAtt.addFlashAttribute("msg", "Edit Failure");
		return "redirect:/TodoList";
	}

	@PostMapping("/save")
	public String saveTodo(@ModelAttribute Todo todo, RedirectAttributes redAtt) {
		if (todoService.saveTodo(todo)) {
//			redAtt.addFlashAttribute("todoList", todoService.getAllTodos());
			redAtt.addFlashAttribute("msg", "Save Success");
			return "redirect:/TodoList";
		}
		redAtt.addFlashAttribute("msg", "Save Failure");
		return "redirect:/AddTodo";
	}

	@GetMapping("/edit/{id}")
	public String editTodo(@PathVariable Integer id, Model model) {
		Todo todo = todoService.getTodo(id);
		model.addAttribute("existTodo", todo);
		return "EditTodo";
	}

	@PostMapping("/update")
	public String updateTodo(@ModelAttribute Todo todo, RedirectAttributes redAtt) {
		if (todoService.updateTodo(todo)) {
			redAtt.addFlashAttribute("msg", "Edit Success");
			return "redirect:/TodoList";
		}
		redAtt.addFlashAttribute("msg", "Edit Failure");
		return "redirect:/EditTodo" + todo.getId();
	}

	@RequestMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Integer id, RedirectAttributes redAtt) {
		if (todoService.deleteTodo(id)) {
			redAtt.addFlashAttribute("msg", "Delete Success");
			return "redirect:/TodoList";
		}
		redAtt.addFlashAttribute("msg", "Delete Failure");
		return "redirect:/TodoList";
	}
}