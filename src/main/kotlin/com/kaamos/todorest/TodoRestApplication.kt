package com.kaamos.todorest

import com.kaamos.todorest.dao.TodoDAO;
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoRestApplication

fun main(args: Array<String>) {
	runApplication<TodoRestApplication>(*args);
	TodoDAO().initDatabase();
}

