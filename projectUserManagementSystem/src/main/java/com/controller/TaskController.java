package com.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.model.Task;
import com.model.User;
import com.service.TaskService;
import com.service.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	
    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping("/assign/{userId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long userId, @RequestBody Task task) {
        Task assignedTask = taskService.assignTaskToUser(userId, task);
        return new ResponseEntity<>(assignedTask, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        List<Task> tasks = taskService.getTasksByUser(user);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{taskId}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long taskId, @RequestBody Task task) {

        Task updatedTask = taskService.updateTaskStatus(taskId, task.getStatus());

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }
    

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable Long taskId) {
       
        Task task = new Task();
        task.setId(taskId);
        taskService.deleteTask(task);
        return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
    }
}