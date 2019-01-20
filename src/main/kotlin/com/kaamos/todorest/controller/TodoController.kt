package com.kaamos.todorest.controller;

import com.kaamos.todorest.model.ToDo;
import com.kaamos.todorest.dao.TodoDAO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class TodoController {
    @PostMapping("/addtask")
    fun addTask(@RequestParam task: String, user: String, timestamp: Long, completed: Boolean): String {
        val todo = ToDo(null, task, user, timestamp, completed);
        TodoDAO().addTask(todo);
        return "Task added!";
    }

    @GetMapping("/gettasks")
    fun getTasks(): MutableList<ToDo> {
        println("Hello");
        var toDos = TodoDAO().getTasks();
        println(toDos);
        return toDos;
    }
}
