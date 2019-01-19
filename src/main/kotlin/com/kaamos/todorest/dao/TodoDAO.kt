package com.kaamos.todorest.dao;

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction;
import com.kaamos.todorest.dao.DBConfig;

object ToDo : Table("todo") {
    val id = integer("id").autoIncrement().primaryKey();
    val task = varchar("task", 100);
    val user = varchar("user", 20);
    val timestamp = long("timestamp");
}

class TodoDAO {


    val db by lazy {
        Database.connect(url = DBConfig().url, driver = DBConfig().driver, user = DBConfig().user, password = DBConfig().password);
    }

    fun initDatabase() {
        db;
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(ToDo); 
        }
    }
}
