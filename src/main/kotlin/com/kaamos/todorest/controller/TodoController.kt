package com.kaamos.todorest.controller;

import com.kaamos.todorest.model.ToDo;
import com.kaamos.todorest.dao.TodoDAO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class TodoController {
    @PostMapping("/addtodo")
    fun addTask(@RequestBody newTodo: ToDo): String {
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
