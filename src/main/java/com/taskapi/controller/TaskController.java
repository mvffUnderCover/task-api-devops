package com.taskapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.taskapi.model.Task;
import com.taskapi.services.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public List<Task> getTasks() {
        return service.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.saveTask(task);
    }

    @GetMapping("/{id}")
    public Task getTask(@PathVariable Long id) {
        return service.getTaskById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        service.deleteTask(id);
    }

    @PutMapping("/{id}/cancel")
    public Task cancelTask(@PathVariable Long id){
        return service.cancelTask(id);
    }
}