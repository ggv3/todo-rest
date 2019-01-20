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

    fun updateTodo(updatedTodo: toDoObject) {
        db;
        transaction {
            addLogger(StdOutSqlLogger);

            // We need to create new variables so that the data types will be correct.
            var updatedTask: String = updatedTodo.task;
            var updatedUser: String = updatedTodo.user;
            var updatedTimestamp: Long = updatedTodo.timestamp;
            var updatedCompleted: Boolean = updatedTodo.completed;

            ToDo.update({ToDo.id eq updatedTodo.id!!}) {
                with(SqlExpressionBuilder) {
                    it[ToDo.task] = updatedTask;
                    it[ToDo.user] = updatedUser;
                    it[ToDo.timestamp] = updatedTimestamp;
                    it[ToDo.completed] = updatedCompleted;
                }
            };
        };
    };

    fun getTodos(): MutableList<toDoObject> {
        var toDos = mutableListOf<toDoObject>()
        db;
        transaction {
            addLogger(StdOutSqlLogger);
            for (todo in ToDo.selectAll()) {
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

    fun addTodo(newTodo: toDoObject) {
        db;
        transaction {
            addLogger(StdOutSqlLogger);
            ToDo.insert {
                it[task] = newTodo.task;
                it[user] = newTodo.user;
                it[timestamp] = newTodo.timestamp;
                it[completed] = newTodo.completed;
            };
        };
    };
};
