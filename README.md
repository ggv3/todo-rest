# REST API for a Todo project made with Kotlin

## Manual

This API has all the basic CRUD endpoints for a ToDo app and it uses JetBrains Exposed SQL Framework for all the database functionalities.

Every endpoint uses a single ToDo-object with a following structure:

```
{
    id: Integer,
    task: String,
    user: String,
    timestamp: Long,
    completed: Boolean
}
```

### Create
If you want to create new tasks using the `.../addtodo` endpoint, you need to send a JSON object with the same parameters (id is not needed) in the request body.

### List all

Listing all tasks in the database doesnt need any parameters or Objects to be sent to the backend. Just call `.../gettodos` and it will return all tasks as a list of JSON objects

### Update

Updating a task with `.../updatetodo` endpoint will need that complete object in the request body. We need to know what task you are updating and what data are we saving.

### Delete

Deleting a task will also need that same JSON object, technically it will be enough if you only declare the id of the task that is to be removed in the request body for the `.../deletetodo` endpoint.



## Technologies

* Kotlin version 1.2.71
* Spring Boot 2.1.2.RELEASE
* JetBrains Exposed (https://github.com/JetBrains/Exposed)

