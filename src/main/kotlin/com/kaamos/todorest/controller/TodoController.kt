package com.kaamos.todorest.controller;

import com.kaamos.todorest.model.ToDo;
import com.kaamos.todorest.dao.TodoDAO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
class TodoController {
    @PostMapping("/addtask")
    fun addTask(@RequestParam task: String, user: String, timestamp: Long): String {
        val todo = ToDo(task, user, timestamp);
        TodoDAO().addTask(todo);
        return "Task added!";
    }
}