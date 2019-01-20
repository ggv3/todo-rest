package com.kaamos.todorest.dao;

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction;
import com.kaamos.todorest.dao.DBConfig;
import com.kaamos.todorest.model.ToDo as toDoObject

class TodoDAO {

    object ToDo : Table("todo") {
    var id = integer("id").autoIncrement().primaryKey();
    var task = varchar("task", 100);
    var user = varchar("user", 20);
    var timestamp = long("timestamp");
    var completed = bool("completed");
    };

    val db by lazy {
        Database.connect(url = DBConfig().url, driver = DBConfig().driver, user = DBConfig().user, password = DBConfig().password);
    };

    fun initDatabase() {
        db;
        transaction {
            addLogger(StdOutSqlLogger);
            SchemaUtils.create(ToDo); 
        };
    };

    fun getTasks(): MutableList<toDoObject> {
        var toDos = mutableListOf<toDoObject>()
        db;
        transaction {
            addLogger(StdOutSqlLogger);
            for (todo in ToDo.selectAll()) {
                println(todo);
                var id = todo[ToDo.id];
                var task = todo[ToDo.task];
                var user = todo[ToDo.user];
                var timestamp = todo[ToDo.timestamp];
                var completed = todo[ToDo.completed];
                val todoObject = toDoObject(id, task, user, timestamp, completed);
                toDos.add(todoObject);
            };
        };
        return toDos;
    };

    fun addTask(todo: toDoObject) {
        db;
        transaction {
            addLogger(StdOutSqlLogger);
            ToDo.insert {
                it[task] = todo.task;
                it[user] = todo.user;
                it[timestamp] = todo.timestamp;
                it[completed] = todo.completed;
            };
        };
    };
};
