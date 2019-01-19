package com.kaamos.todorest.model;

// This class exists just so I can pass an object as a parameter to TodoDAO 
// instead of passing every attribute separately. There must be an easier way 
// to do this, but I haven't figured it out yet.
data class ToDo(var task: String, var user: String, var timestamp: Long)