package com.kaamos.todorest.controller;

import com.kaamos.todorest.model.ToDo;
import com.kaamos.todorest.dao.TodoDAO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class TodoController {
    @PostMapping("/addtodo")
    fun addTask(@RequestParam task: String, user: String, timestamp: Long, completed: Boolean): String {
        val newTodo = ToDo(null, task, user, timestamp, completed);
        TodoDAO().addTodo(newTodo);
        return "Todo added!";
    }

    @PostMapping("/updatetodo")
    fun updateTodo(@RequestBody updatedTodo: ToDo): String {
        TodoDAO().updateTodo(updatedTodo);
        return "Todo updated!";
    }

    @GetMapping("/gettodos")
    fun getTodos(): MutableList<ToDo> {
        var toDos = TodoDAO().getTodos();
        return toDos;
    }

    @PostMapping("/deletetodo")
    fun deleteTodo(@RequestBody todoToDelete: ToDo): String {
        TodoDAO().deleteTodo(todoToDelete);
        return "Todo deleted!";
    }
}
